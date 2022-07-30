package com.atguigu.book.pojo;

//我们应该还需要设计一个Cart类，代表购物车这个实体
public class CartItem {
    private Integer id ;
    private Book book ;
    private Integer buyCount ;
    private User uesr ;

    public CartItem(){}

    public CartItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUesr() {
        return uesr;
    }

    public void setUesr(User uesr) {
        this.uesr = uesr;
    }
}
