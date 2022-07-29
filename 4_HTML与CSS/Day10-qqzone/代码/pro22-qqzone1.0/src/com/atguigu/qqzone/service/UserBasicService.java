package com.atguigu.qqzone.service;

import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicService {
    UserBasic login(String loginId , String pwd );
    List<UserBasic> getFriendList(UserBasic userBasic);
    //根据id获取指定用户信息
    UserBasic getUserBasicById(Integer id);
}
