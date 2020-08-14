package com.czxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
*
*@author zhandehuang@itcast.cn
*@date 2020/2/5 0005
*@version 1.0
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product  {

    private Long pid;


    private String pname;


    private Double marketPrice;


    private String pimage;


    private Date pdate;



    private String pdesc;


    private Long cid;

    private Category category;

    private static final long serialVersionUID = 1L;
}