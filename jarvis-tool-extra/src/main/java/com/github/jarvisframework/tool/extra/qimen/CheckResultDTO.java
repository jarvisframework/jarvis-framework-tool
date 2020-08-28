package com.github.jarvisframework.tool.extra.qimen;

import com.taobao.api.internal.spi.CheckResult;

import java.util.Map;

/**
 * <p>校验返回对象</p>
 *
 * @author Doug Wang
 * @date 2019-11-19 14:42:34
 */
public class CheckResultDTO extends CheckResult {

    private Map<String, String> requestParams;

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
