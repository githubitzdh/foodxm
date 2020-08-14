package com.czxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


/**
*
*@author zhandehuang@itcast.cn
*@date 2020/3/5 0005
*@version 1.0
**/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderitem")
public class Orderitem implements Serializable {
    @Id
    @Column(name = "itemid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemid;

    @Column(name = "`count`")
    private Integer count;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "pid")
    private Long pid;

    @Column(name = "oid")
    private Long oid;

    @Column(name = "pname")
    private String pname;

    private static final long serialVersionUID = 1L;
}