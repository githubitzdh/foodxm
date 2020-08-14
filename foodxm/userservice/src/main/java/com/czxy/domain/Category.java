package com.czxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
*
*@author zhandehuang@itcast.cn
*@date 2020/2/5 0005
*@version 1.0
**/

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Category {

    private Long cid;


    private String cname;

    private static final long serialVersionUID = 1L;
}