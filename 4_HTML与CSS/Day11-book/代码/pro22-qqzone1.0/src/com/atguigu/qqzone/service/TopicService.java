package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic) ;
    //根据id获取特定topic
    Topic getTopicById(Integer id);

    //根据id获取指定的topic信息，包含这个topic关联的作者信息
    public Topic getTopic(Integer id);

    //删除特定的topic
    void delTopic(Integer id);
}
