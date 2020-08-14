package com.czxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
*
*@author zhandehuang@itcast.cn
*@date 2020/3/5 0005
*@version 1.0
**/

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Orderitem  {

    private Long itemid;


    private Integer count;


    private Double subtotal;


    private Long pid;


    private Long oid;


    private String pname;

    private static final long serialVersionUID = 1L;
}