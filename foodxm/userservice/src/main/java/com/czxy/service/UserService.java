package com.czxy.service;


import com.czxy.domain.User;
import com.czxy.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
*
*@author zhandehuang@itcast.cn
*@date 2020/1/16 0016
*@version 1.0
**/

public interface UserService{



    User login(User user);


    void insert(User user);


    void del(Long id);

    User searchbyid(Long id);

    void upde(User user);

    PageInfo<User> selectall(UserVo userVo);

    PageInfo<User> searchadmin(UserVo userVo);


    String CheckUsername(String username);


    List<User> selectuserall();
}
