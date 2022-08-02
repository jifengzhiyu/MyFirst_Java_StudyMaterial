package com.atguigu.fruit.controllers;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FruitController {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    private String update(Integer fid , String fname , Integer price , Integer fcount , String remark ){
        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname, price ,fcount ,remark ));
        //4.资源跳转
        return "redirect:fruit.do";
    }

    private String edit(Integer fid , HttpServletRequest request){
        if(fid!=null){
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
            //super.processTemplate("edit",request,response);
            return "edit";
        }
        return "error" ;
    }

    private String del(Integer fid  ){
        if(fid!=null){
            fruitDAO.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname , Integer price , Integer fcount , String remark ) {
        Fruit fruit = new Fruit(0,fname , price , fcount , remark ) ;
        fruitDAO.addFruit(fruit);
        return "redirect:fruit.do";
    }

    private String index(String oper , String keyword , Integer pageNo , HttpServletRequest request ) {
        HttpSession session = request.getSession() ;

        if(pageNo==null){
            pageNo = 1;
        }
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            pageNo = 1 ;
            if(StringUtil.isEmpty(keyword)){
                keyword = "" ;
            }
            session.setAttribute("keyword",keyword);
        }else{
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj ;
            }else{
                keyword = "" ;
            }
        }

        // 重新更新当前页的值
        session.setAttribute("pageNo",pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword , pageNo);
        session.setAttribute("fruitList",fruitList);

        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount+5-1)/5 ;
        session.setAttribute("pageCount",pageCount);

        return "index" ;
    }
}
