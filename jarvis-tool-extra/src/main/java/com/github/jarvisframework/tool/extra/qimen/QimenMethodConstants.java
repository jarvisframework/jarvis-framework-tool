package com.github.jarvisframework.tool.extra.qimen;

/**
 * <p>奇门Method公用常量类</p>
 *
 * @author Doug Wang
 * @date 2019-11-15 14:19:10
 */
public abstract class QimenMethodConstants {

    // --------------------------WMS--------------------------

    /**
     * 商品同步接口
     */
    public static final String SINGLEITEM_SYNCHRONIZE = "singleitem.synchronize";
    /**
     * 入库单创建接口
     */
    public static final String ENTRYORDER_CREATE = "entryorder.create";
    /**
     * 退货入库单创建接口
     */
    public static final String RETURNORDER_CREATE = "returnorder.create";
    /**
     * 出库单创建接口
     */
    public static final String STOCKOUT_CREATE = "stockout.create";
    /**
     * 发货单创建(批量)
     */
    public static final String DELIVERYORDER_BATCHCREATE = "deliveryorder.batchcreate";
    /**
     * 发货单创建(单个)
     */
    public static final String DELIVERYORDER_CREATE = "deliveryorder.create";
    /**
     * 发货单批量创建
     */
    public static final String DELIVERYORDER_BATCHCREATE_ANSWER = "deliveryorder.batchcreate.answer";
    /**
     * 订单流水查询
     */
    public static final String ORDERPROCESS_QUERY = "orderprocess.query";
    /**
     * 单据取消
     */
    public static final String ORDER_CANCEL = "order.cancel";
    /**
     * 商品库存查询(多商品)
     */
    public static final String INVENTORY_QUERY = "inventory.query";
    /**
     * 商品库存查询(多条件)
     */
    public static final String STOCK_QUERY = "stock.query";

    // --------------------------ERP--------------------------

    /**
     * 发货确认
     */
    public static final String TAOBAO_QIMEN_DELIVERYORDER_CONFIRM = "taobao.qimen.deliveryorder.confirm";
    /**
     * 批量发货确认
     */
    public static final String TAOBAO_QIMEN_DELIVERYORDER_BATCHCONFIRM = "taobao.qimen.deliveryorder.batchconfirm";
    /**
     * 出库单确认接口
     */
    public static final String TAOBAO_QIMEN_STOCKOUT_CONFIRM = "taobao.qimen.stockout.confirm";
    /**
     * 退货入库单确认接口
     */
    public static final String TAOBAO_QIMEN_RETURNORDER_CONFIRM = "taobao.qimen.returnorder.confirm";
    /**
     * 入库单确认接口
     */
    public static final String TAOBAO_QIMEN_ENTRYORDER_CONFIRM = "taobao.qimen.entryorder.confirm";
    /**
     * 订单流水通知
     */
    public static final String TAOBAO_QIMEN_ORDERPROCESS_REPORT = "taobao.qimen.orderprocess.report";
    /**
     * 库存盘点通知
     */
    public static final String TAOBAO_QIMEN_INVENTORY_REPORT = "taobao.qimen.inventory.report";
    /**
     * 库存异动通知
     */
    public static final String TAOBAO_QIMEN_STOCKCHANGE_REPORT = "taobao.qimen.stockchange.report";
    /**
     * 发货单SN通知接口
     */
    public static final String TAOBAO_QIMEN_SN_REPORT = "taobao.qimen.sn.report";
    /**
     * 单据取消
     */
    public static final String TAOBAO_QIMEN_ORDER_CANCEL = "taobao.qimen.order.cancel";
    /**
     * 入库单查询接口
     */
    public static final String TAOBAO_QIMEN_ENTRYORDER_QUERY = "taobao.qimen.entryorder.query";
    /**
     * 奇门仓库查询接口
     */
    public static final String TAOBAO_QIMEN_WAREHOUSE_QUERY = "taobao.qimen.warehouse.query";
    /**
     * 心跳报文
     */
    public static final String SERVICE_HEARTBEAT = "client.heartbeat";

}
