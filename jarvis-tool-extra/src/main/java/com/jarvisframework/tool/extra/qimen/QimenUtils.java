package com.jarvisframework.tool.extra.qimen;

import com.jarvisframework.tool.core.util.StringUtils;
import com.jarvisframework.tool.core.util.XmlParseUtils;
import com.jarvisframework.tool.http.HttpUtils;
import com.taobao.api.Constants;
import com.taobao.api.internal.spi.SpiUtils;
import com.taobao.api.internal.util.TaobaoUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * <p>奇门平台工具类</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-15 18:41:07
 */
public class QimenUtils {

    private static final Log LOG = LogFactory.getLog(QimenUtils.class);

    /**
     * 获取请求单号
     *
     * @param method
     * @param body
     * @return
     */
    public static String getRequestOrderNo(String method, String body) {
        switch (method) {
            case QimenMethodConstants.SINGLEITEM_SYNCHRONIZE:
                return QimenUtils.itemCodeOf(body);
            case QimenMethodConstants.STOCKOUT_CREATE:
                return QimenUtils.stockoutOrderCodeOf(body);
            case QimenMethodConstants.ENTRYORDER_CREATE:
                return QimenUtils.entryOrderCodeOf(body);
            case QimenMethodConstants.TAOBAO_QIMEN_DELIVERYORDER_CONFIRM:
                return QimenUtils.deliveryConfirmOrderCodeOf(body);
            default:
                return StringUtils.EMPTY;
        }
    }

    /**
     * 从XML字符串中获取出库单号
     *
     * @param xmlStr
     * @return
     */
    public static final String itemCodeOf(String xmlStr) {
        return XmlParseUtils.getText(xmlStr, "request/item/itemCode");
    }

    /**
     * 从XML字符串中获取出库单号
     *
     * @param xmlStr
     * @return
     */
    public static final String stockoutOrderCodeOf(String xmlStr) {
        return XmlParseUtils.getText(xmlStr, "request/deliveryOrder/deliveryOrderCode");
    }

    /**
     * 从XML字符串中获取入库单号
     *
     * @param xmlStr
     * @return
     */
    public static final String entryOrderCodeOf(String xmlStr) {
        return XmlParseUtils.getText(xmlStr, "request/entryOrder/entryOrderCode");
    }

    public static final String deliveryConfirmOrderCodeOf(String xmlStr) {
        return XmlParseUtils.getText(xmlStr, "request/deliveryOrder/deliveryOrderCode");
    }

    /**
     * 校验奇门请求签名
     *
     * @param request HttpServletRequest对象实例
     * @param secret  APP密钥
     * @return 校验结果
     */
    public static final CheckResultDTO checkSign(HttpServletRequest request, String secret) throws IOException, QimenException {
        CheckResultDTO result = new CheckResultDTO();
        String contentType = HttpUtils.getContentType(request);
        String charset = HttpUtils.getResponseCharset(contentType);

        Set<String> contentTypes = new HashSet<>();
        contentTypes.add(Constants.CTYPE_FORM_DATA);
        contentTypes.add(Constants.CTYPE_TEXT_XML);
        contentTypes.add(Constants.CTYPE_APPLICATION_XML);
        contentTypes.add(Constants.CTYPE_TEXT_PLAIN);
        contentTypes.add(Constants.CTYPE_APP_JSON);
        if (contentTypes.contains(contentType)) {
            Map<String, String> params = new HashMap<>(9);
            // 1. 获取header参数
            Map<String, String> headerMap = SpiUtils.getHeaderMap(request, charset);
            params.putAll(headerMap);
            // 2. 获取url参数
            if (null != request.getQueryString()) {
                Map<String, String> queryMap = SpiUtils.getQueryMap(request, charset);
                params.putAll(queryMap);
            }
            // 3. 获取form参数
            Map<String, String> formMap = SpiUtils.getFormMap(request, params);
            params.putAll(formMap);
            // 4.获取body
            String body = HttpUtils.getStreamAsString(request.getInputStream(), charset);
            // 5.验证签名
            boolean valid = checkSignInternal(params, body, secret, charset);
            result.setSuccess(valid);
            result.setRequestParams(params);
            result.setRequestBody(body);
        } else {
            throw new QimenException("Unspported API request");
        }
        return result;
    }

    private static boolean checkSignInternal(Map<String, String> params, String body, String secret, String charset) throws IOException {


        String remoteSign = params.get(Constants.SIGN);
        params.remove(Constants.SIGN);
        String localSign = TaobaoUtils.signTopRequest(params, body, secret, params.get(Constants.SIGN_METHOD));
        if (localSign.equals(remoteSign)) {
            return true;
        } else {
            String paramStr = getParamStrFromMap(params);
            LOG.error("checkTopSign error^_^remoteSign=" + remoteSign + "^_^localSign=" + localSign + "^_^paramStr=" + paramStr + "^_^body=" + body);
            return false;
        }
    }

    private static String getParamStrFromMap(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            String[] keys = params.keySet().toArray(new String[0]);
            Arrays.sort(keys);

            for (String name : keys) {
                if (!"sign".equals(name)) {
                    sb.append(name);
                    sb.append(params.get(name));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 根据method判断是否为ERP调用
     *
     * @param method
     * @return
     */
    public static boolean isErpCall(String method) {
        switch (method) {
            case QimenMethodConstants.SINGLEITEM_SYNCHRONIZE:
            case QimenMethodConstants.ENTRYORDER_CREATE:
            case QimenMethodConstants.RETURNORDER_CREATE:
            case QimenMethodConstants.STOCKOUT_CREATE:
            case QimenMethodConstants.DELIVERYORDER_BATCHCREATE:
            case QimenMethodConstants.DELIVERYORDER_CREATE:
            case QimenMethodConstants.DELIVERYORDER_BATCHCREATE_ANSWER:
            case QimenMethodConstants.ORDERPROCESS_QUERY:
            case QimenMethodConstants.ORDER_CANCEL:
            case QimenMethodConstants.INVENTORY_QUERY:
            case QimenMethodConstants.STOCK_QUERY:
                return true;
            default:
                return false;
        }
    }

    /**
     * 根据method判断是否为WMS调用
     *
     * @param method
     * @return
     */
    public static boolean isWmsCall(String method) {
        switch (method) {
            case QimenMethodConstants.TAOBAO_QIMEN_DELIVERYORDER_CONFIRM:
            case QimenMethodConstants.TAOBAO_QIMEN_DELIVERYORDER_BATCHCONFIRM:
            case QimenMethodConstants.TAOBAO_QIMEN_STOCKOUT_CONFIRM:
            case QimenMethodConstants.TAOBAO_QIMEN_RETURNORDER_CONFIRM:
            case QimenMethodConstants.TAOBAO_QIMEN_ENTRYORDER_CONFIRM:
            case QimenMethodConstants.TAOBAO_QIMEN_ORDERPROCESS_REPORT:
            case QimenMethodConstants.TAOBAO_QIMEN_INVENTORY_REPORT:
            case QimenMethodConstants.TAOBAO_QIMEN_STOCKCHANGE_REPORT:
            case QimenMethodConstants.TAOBAO_QIMEN_SN_REPORT:
            case QimenMethodConstants.TAOBAO_QIMEN_ORDER_CANCEL:
            case QimenMethodConstants.TAOBAO_QIMEN_ENTRYORDER_QUERY:
            case QimenMethodConstants.TAOBAO_QIMEN_WAREHOUSE_QUERY:
                return true;
            default:
                return false;
        }
    }
}
