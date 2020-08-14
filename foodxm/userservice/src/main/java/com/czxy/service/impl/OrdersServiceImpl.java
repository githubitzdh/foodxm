package com.czxy.service.impl;

import com.czxy.domain.Orderitem;
import com.czxy.domain.Orders;
import com.czxy.domain.Product;
import com.czxy.feign.OFeign;
import com.czxy.mapper.OrdersMapper;
import com.czxy.service.OrdersService;
import com.czxy.vo.BaseResult;
import com.czxy.vo.OrderVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/4 0004
 **/

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private OFeign oFeign;
    @Override
    public PageInfo<Orders> page(OrderVo orderVo) {
        PageHelper.startPage(orderVo.getPageNum(),orderVo.getPageSize());
        if(StringUtils.isNotBlank(orderVo.getStpostTime())){
            orderVo.setStpostTime(orderVo.getStpostTime()+" 00:00:00");
        }
        if(StringUtils.isNotBlank(orderVo.getEndpostTime())){
            orderVo.setEndpostTime(orderVo.getEndpostTime()+" 23:59:59");
        }

      List<Orders>ordersList= ordersMapper.page(orderVo);
        for (Orders orders : ordersList) {
            BaseResult<List<Orderitem>> orderitemlist = oFeign.findorid(orders.getOid());
          orders.setOlist(orderitemlist.getData());
        }

        return new PageInfo<>(ordersList);
    }

    @Override
    public void addc(Orders orders) {
        orders.setStatus(1);
        ordersMapper.insert(orders);
         double total=0.0;
        for (Long pid : orders.getChecklist()) {
            BaseResult<Product> bb = oFeign.searchbyid(pid);
            System.out.println(bb.getData());
            Product da = bb.getData();
            System.out.println(da);
            Orderitem orderitem = new Orderitem(null, 1, da.getMarketPrice(), pid, orders.getOid(), da.getPname());
            oFeign.addorderitem(orderitem);
            total+=orderitem.getCount()*orderitem.getSubtotal();
        }
        orders.setTotal(total);
        ordersMapper.updateByPrimaryKeySelective(orders);

    }

    @Override
    public void del(Long id) {
        ordersMapper.deleteByPrimaryKey(id);
       oFeign.delos(id);
    }

    @Override
    public Orders searchbyid(Long id) {
        Orders orders = ordersMapper.selectByPrimaryKey(id);
        BaseResult<List<Orderitem>> a = oFeign.findorid(orders.getOid());
        orders.setOlist(a.getData());
       return orders;

    }

    @Override
    public void upde( Orders orders) {
        ordersMapper.updateByPrimaryKeySelective(orders);

    }

    @Override
    public PageInfo<Orders> oneopage(OrderVo orderVo, Long id) {
        PageHelper.startPage(orderVo.getPageNum(),orderVo.getPageSize());
        if(StringUtils.isNotBlank(orderVo.getStpostTime())){
            orderVo.setStpostTime(orderVo.getStpostTime()+" 00:00:00");
        }
        if(StringUtils.isNotBlank(orderVo.getEndpostTime())){
            orderVo.setEndpostTime(orderVo.getEndpostTime()+" 23:59:59");
        }

        List<Orders>ordersList= ordersMapper.oneopage(orderVo,id);
        for (Orders orders : ordersList) {
            BaseResult<List<Orderitem>> orderitemlist = oFeign.findorid(orders.getOid());
            orders.setOlist(orderitemlist.getData());
        }

        return new PageInfo<>(ordersList);
    }

    @Override
    public void oneadd(Orders orders, Long id) {
        orders.setUid(id);
        orders.setStatus(1);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       sdf.format(date);
        orders.setOrdertime( new Date());
        ordersMapper.insert(orders);
        double total=0.0;
        for (Long pid : orders.getChecklist()) {
            BaseResult<Product> bb = oFeign.searchbyid(pid);
            System.out.println(bb.getData());
            Product da = bb.getData();
            System.out.println(da);
            Orderitem orderitem = new Orderitem(null, 1, da.getMarketPrice(), pid, orders.getOid(), da.getPname());
            oFeign.addorderitem(orderitem);
            total+=orderitem.getCount()*orderitem.getSubtotal();
        }
        orders.setTotal(total);
        ordersMapper.updateByPrimaryKeySelective(orders);

    }

    @Override
    public void upstatue(Long id) {
        Orders orders = ordersMapper.selectByPrimaryKey(id);
     orders.setStatus(2);
     ordersMapper.updateByPrimaryKeySelective(orders);
    }


}


