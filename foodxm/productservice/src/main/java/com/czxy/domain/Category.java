package com.czxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
*
*@author zhandehuang@itcast.cn
*@date 2020/2/5 0005
*@version 1.0
**/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @Column(name = "cid")
    private Long cid;

    @Column(name = "cname")
    private String cname;
    private static final long serialVersionUID = 1L;

}