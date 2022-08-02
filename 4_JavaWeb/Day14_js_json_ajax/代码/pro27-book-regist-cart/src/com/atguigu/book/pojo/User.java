package com.atguigu.book.pojo;

import java.util.List;

public class User {
    private Integer id ;
    private String uname ;
    private String pwd ;
    private String email;
    private Integer role = 0 ;

    private Cart cart ;
    private List<OrderBean> orderList ;     //1:N

    public User(){}

    public User(Integer id) {
        this.id = id;
    }

    public User(String uname, String pwd, String email, Integer role) {
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderBean> orderList) {
        this.orderList = orderList;
    }
}
