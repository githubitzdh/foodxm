package com.czxy.service;


import com.czxy.domain.Orderitem;

import java.util.List;

/**
*
*@author zhandehuang@itcast.cn
*@date 2020/3/5 0005
*@version 1.0
**/

public interface OrderitemService{



    List<Orderitem> findorid(Long id);

    void addorderitem(Orderitem product);

    void delos(Long id);


    List<Orderitem> selectLookorder(Long id);
}
