package com.atguigu.book.dao;

import com.atguigu.book.pojo.OrderItem;

public interface OrderItemDAO {
    //添加订单项
    void addOrderItem(OrderItem orderItem);
}
