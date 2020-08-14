package com.czxy.vo;

import lombok.Data;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/4 0004
 **/
@Data
public class OrderVo {
    private Integer pageNum;
    private Integer pageSize;
    private  String stpostTime;
    private  String endpostTime;
    private String status;
}
