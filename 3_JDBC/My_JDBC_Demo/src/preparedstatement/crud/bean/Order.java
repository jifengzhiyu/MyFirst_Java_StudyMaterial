package preparedstatement.crud.bean;/**
 * @author jifengzhiyu
 * @create 2022-06-30 16:31
 */

import java.sql.Date;

/**
 *@ClassName Order
 *@Description TODO
 *@Author kaixin
 *@Date 2022/6/30 16:31
 *@Version 1.0
 */
public class Order {
    private int orderId;
    private String orderName;
    private Date orderDate;


    public Order() {
        super();
    }

    public Order(int orderId, String orderName, Date orderDate) {
        super();
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", orderName=" + orderName + ", orderDate=" + orderDate + "]";
    }
}