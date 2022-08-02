package com.atguigu.book.controller;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;

public class CartController {

    private CartItemService cartItemService ;

    //加载当前用户的购物车信息
    public String index(HttpSession session){
        User user =(User)session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser",user);
        return "cart/cart";
    }

    public String addCart(Integer bookId , HttpSession session){
        User user = (User)session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId),1,user);
        //将指定的图书添加到当前用户的购物车中
        cartItemService.addOrUpdateCartItem(cartItem,user.getCart());

        return "redirect:cart.do";
    }

    public String editCart(Integer cartItemId , Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId , buyCount));
        return "";
    }

    public String cartInfo(HttpSession session){
        User user =(User)session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);

        //调用Cart中的三个属性的get方法，目的是在此处计算这三个属性的值，否则这三个属性为null，
        //导致的结果就是下一步的gson转化时，为null的属性会被忽略
        cart.getTotalBookCount();
        cart.getTotalCount();
        cart.getTotalMoney();

        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:"+cartJsonStr ;
    }
}
