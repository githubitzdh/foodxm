package com.czxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
*
*@author zhandehuang@itcast.cn
*@date 2020/1/16 0016
*@version 1.0
**/

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long uid;


    private String username;


    private String password;


    private String utype;


    private String tname;


    private String sex;


    private Integer age;


    private String telephone;


    private String addrs;


    private Date savetime;


    private String desc;


}