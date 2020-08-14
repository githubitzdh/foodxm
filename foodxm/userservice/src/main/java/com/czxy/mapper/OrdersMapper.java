package com.czxy.mapper;

import com.czxy.domain.Orders;
import com.czxy.vo.OrderVo;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/5 0005
 **/


public interface OrdersMapper extends Mapper<Orders> {
    @Select("<script>    select  * from orders \n" +
            "    <where>\n" +
            "    <if test=\"status!=null  and status!=''\">\n" +
            "      and status = #{status}\n" +
            "    </if>\n" +
            "    <if test=\"stpostTime!=null  and stpostTime!=''\">\n" +
            "      and ordertime >= #{stpostTime}\n" +
            "    </if>\n" +
            "    <if test=\"endpostTime!=null  and endpostTime!=''\">\n" +
            "      <![CDATA[and ordertime <= #{endpostTime}]]>\n" +
            "    </if>\n" +
            "    </where></script>")
    @Results({
            @Result(property = "oid",column = "oid"),
            @Result(property = "ordertime",column = "ordertime"),
            @Result(property = "name",column = "name"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "status",column = "status"),
            @Result(property = "userlist",column = "uid",one = @One(select = "com.czxy.mapper.UserMapper.selectByPrimaryKey")),

    })
    List<Orders> page(OrderVo orderVo);


    @Select("<script> \tselect  * from orders where uid=#{id}\n" +
            "\t\t   <if test=\"orderVo.status!=null  and orderVo.status!=''\">\n" +
            "\t\t     and status = #{orderVo.status}\n" +
            "\t   </if>\n" +
            "\t\t   <if test=\"orderVo.stpostTime!=null  and orderVo.stpostTime!=''\">\n" +
            "\t    and ordertime >= #{orderVo.stpostTime}\n" +
            "\t   </if>\n" +
            "\t <if test=\"orderVo.endpostTime!=null  and orderVo.endpostTime!=''\">\n" +
            "\t      <![CDATA[and ordertime <= #{orderVo.endpostTime}]]>\n" +
            "   </if></script>")
    @Results({
            @Result(property = "oid",column = "oid"),
            @Result(property = "ordertime",column = "ordertime"),
            @Result(property = "name",column = "name"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "status",column = "status"),
            @Result(property = "userlist",column = "uid",one = @One(select = "com.czxy.mapper.UserMapper.selectByPrimaryKey")),

    })
    List<Orders> oneopage(@Param("orderVo") OrderVo orderVo, @Param("id") Long id);
}