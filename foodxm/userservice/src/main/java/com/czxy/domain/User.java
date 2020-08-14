package com.czxy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
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
@Table(name = "`user`")
public class User implements Serializable {
    @Id
    @Column(name = "`uid`")
    @GeneratedValue(generator = "JDBC")
    private Long uid;

    @Column(name = "username")
    private String username;

    @Column(name = "`password`")
    private String password;

    @Column(name = "utype")
    private String utype;

    @Column(name = "tname")
    private String tname;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "addrs")
    private String addrs;

    @Column(name = "savetime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date savetime;

    @Column(name = "`desc`")
  private String desc;
    private static final long serialVersionUID = 1L;
}