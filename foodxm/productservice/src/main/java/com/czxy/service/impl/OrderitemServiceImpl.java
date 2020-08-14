package com.czxy.service.impl;

import com.czxy.domain.Orderitem;
import com.czxy.mapper.OrderitemMapper;
import com.czxy.service.OrderitemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
*
*@author zhandehuang@itcast.cn
*@date 2020/3/5 0005
*@version 1.0
**/

@Service
@Transactional
public class OrderitemServiceImpl implements OrderitemService{

    @Resource
    private OrderitemMapper orderitemMapper;

    @Override
    public List<Orderitem> findorid(Long id) {
        return orderitemMapper.findorid(id);
    }

    @Override
    public void addorderitem(Orderitem orderitem) {
        orderitemMapper.insert(orderitem);
    }

    @Override
    public void delos(Long id) {
        Example example = new Example(Orderitem.class);
        Example.Criteria c = example.createCriteria();
       c.andEqualTo("oid",id);
        orderitemMapper.deleteByExample(example);
    }

    @Override
    public List<Orderitem> selectLookorder(Long id) {
        Example example = new Example(Orderitem.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("oid",id);
        return  orderitemMapper.selectByExample(example);
    }


}
