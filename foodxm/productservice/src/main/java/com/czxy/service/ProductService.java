package com.czxy.service;


import com.czxy.domain.Product;
import com.czxy.vo.ProductVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
*
*@author zhandehuang@itcast.cn
*@date 2020/2/5 0005
*@version 1.0
**/

public interface ProductService{



    PageInfo<Product> page(ProductVo productVo);

    void addp(Product product);

    void del(Long id);

    Product searchbyid(Long id);

    void upde(Product product);


    List<Product> spall();


}
