package com.czxy.mapper;

import com.czxy.domain.Product;
import com.czxy.vo.ProductVo;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
*
*@author zhandehuang@itcast.cn
*@date 2020/2/5 0005
*@version 1.0
**/

public interface ProductMapper extends tk.mybatis.mapper.common.Mapper<Product> {

    @Select("<script> select * from product\n" +
            "        <where>\n" +
            "            <if test=\"pname!=null and pname!=''\">\n" +
            "                and pname like #{pname}\n" +
            "            </if>\n" +
            "            <if test=\"cid!=null and cid!=''\">\n" +
            "                and cid = #{cid}\n" +
            "            </if>\n" +
            "        </where></script>")
    @Results({
            @Result(property = "marketPrice",column = "market_price"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "category",column = "cid",one = @One(select = "com.czxy.mapper.CategoryMapper.selectByPrimaryKey"))
    })
    List<Product> page(ProductVo productVo);
}