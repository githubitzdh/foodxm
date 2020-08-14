package com.czxy.mapper;

import com.czxy.domain.Category;
import com.czxy.vo.CategoryVo;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
*
*@author zhandehuang@itcast.cn
*@date 2020/2/5 0005
*@version 1.0
**/

@Mapper
public interface CategoryMapper extends tk.mybatis.mapper.common.Mapper<Category> {

    @Select("<script>select * from category\n" +
            "\t\t<where>\n" +
            "\t\t\t<if test=\"cname!=null and cname!=''\">\n" +
            "\t\t\t\tand cname like #{cname}\n" +
            "\t\t\t</if>\n" +
            "\t\t</where></script>")
    @Results({
            @Result(property = "cid",column = "cid"),
            @Result(property = "cname",column = "cname"),
    })
    List<Category> page(CategoryVo categoryVo);

}