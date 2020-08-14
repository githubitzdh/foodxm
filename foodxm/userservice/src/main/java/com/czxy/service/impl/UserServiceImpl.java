package com.czxy.service.impl;

import com.czxy.domain.User;
import com.czxy.mapper.UserMapper;
import com.czxy.service.UserService;
import com.czxy.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/1/16 0016
 **/

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public void insert(User user) {
        user.setSavetime(new Date());
       if(user.getUtype()==null){
           user.setUtype("普通用户");
       }
        userMapper.insert(user);
    }


    @Override
    public String CheckUsername(String username) {
        User user = new User();
         user.setUsername(username);
        User uu = userMapper.selectOne(user);
        System.out.println(uu);

       if(uu.getUsername().equals(username)){
           return uu.getUsername();
       }
       return null;
    }

    @Override
    public List<User> selectuserall() {
        return userMapper.selectAll();
    }

    @Override
    public void del(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User searchbyid(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void upde(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public PageInfo<User> selectall(UserVo userVo) {
        PageHelper.startPage(userVo.getPageNum(), userVo.getPageSize());
        if (StringUtils.isNotBlank(userVo.getUsername())) {
            userVo.setUsername("%" + userVo.getUsername() + "%");
        }
        List<User> ulist = userMapper.page(userVo);
        System.out.println(ulist);
        return new PageInfo<>(ulist);
    }

    @Override
    public PageInfo<User> searchadmin(UserVo userVo) {
        PageHelper.startPage(userVo.getPageNum(), userVo.getPageSize());
        if (StringUtils.isNotBlank(userVo.getUsername())) {
            userVo.setUsername("%" + userVo.getUsername() + "%");
        }
        List<User> ulist = userMapper.searchadmin(userVo);
        System.out.println(ulist);
        return new PageInfo<>(ulist);
    }




}
