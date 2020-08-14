package com.czxy.mapper;

import com.czxy.domain.Orderitem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
*
*@author zhandehuang@itcast.cn
*@date 2020/3/5 0005
*@version 1.0
**/


public interface OrderitemMapper extends Mapper<Orderitem> {
   @Select("select * from orderitem where oid=#{id}")
    List<Orderitem> findorid(@Param("id")Long id);
}