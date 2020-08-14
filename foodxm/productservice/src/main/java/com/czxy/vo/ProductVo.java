package com.czxy.vo;

import lombok.Data;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/2/5 0005
 **/
@Data
public class ProductVo {
    private Integer pageNum;
    private Integer pageSize;
    private String pname;
    private String cid;

}
