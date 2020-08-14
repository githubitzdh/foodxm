package com.czxy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
*
*@author zhandehuang@itcast.cn
*@date 2020/3/5 0005
*@version 1.0
**/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders implements Serializable {
    @Id
    @Column(name = "oid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    @Column(name = "ordertime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT"+8)
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ordertime;

    @Column(name = "total")
    private Double total;

    @Column(name = "address")
    private String address;

    @Column(name = "`name`")
    private String name;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "`uid`")
    private Long uid;

    @Column(name = "`status`")
    private Integer status;
 private User userlist;
    private List<Long> checklist;
    private List<Orderitem>olist;
    private static final long serialVersionUID = 1L;
}