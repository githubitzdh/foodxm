package com.czxy.service;


import com.czxy.domain.Orders;
import com.czxy.vo.OrderVo;
import com.github.pagehelper.PageInfo;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/4 0004
 **/

public interface OrdersService {

    PageInfo<Orders> page(OrderVo orderVo);

    void addc(Orders orders);

    void del(Long id);

    Orders searchbyid(Long id);

    void upde(Orders orders);


    PageInfo<Orders> oneopage(OrderVo orderVo, Long id);

    void oneadd(Orders orders, Long id);

    void upstatue(Long id);
}


