package com.czxy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "pname")
    private String pname;

    @Column(name = "market_price")
    private Double marketPrice;

    @Column(name = "pimage")
    private String pimage;

    @Column(name = "pdate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date pdate;


    @Column(name = "pdesc")
    private String pdesc;

    @Column(name = "cid")
    private Long cid;

    private Category category;

    private static final long serialVersionUID = 1L;
}