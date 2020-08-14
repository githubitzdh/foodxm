package com.czxy.mapper;

import com.czxy.domain.User;
import com.czxy.vo.UserVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/1/16 0016
 **/


public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {

    /**
     * 登录功能
     *
     * @param user
     * @return
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    User login(User user);


    /**
     * 查询所有用户
     *
     * @param userVo
     * @return
     */
    @Select("<script>select * from user where utype='普通用户'\n" +
            "\t\t<if test=\"username!=null and username!=''\"> \n" +
            "          and username like #{username}\n" +
            "\t </if></script>")
    List<User> page(UserVo userVo);


    /**
     * 查询所有管理员
     *
     * @param userVo
     * @return
     */
    @Select("<script>select * from user where utype='管理员'\n" +
            "\t\t<if test=\"username!=null and username!=''\"> \n" +
            "          and username like #{username}\n" +
            "\t </if></script>")
    List<User> searchadmin(UserVo userVo);
}