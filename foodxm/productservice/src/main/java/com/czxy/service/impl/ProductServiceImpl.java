package com.czxy.service.impl;

import com.czxy.domain.Product;
import com.czxy.mapper.ProductMapper;
import com.czxy.service.ProductService;
import com.czxy.vo.ProductVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
*
*@author zhandehuang@itcast.cn
*@date 2020/2/5 0005
*@version 1.0
**/

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Resource
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> page(ProductVo productVo) {
        PageHelper.startPage(productVo.getPageNum(), productVo.getPageSize());
        if (StringUtils.isNotBlank(productVo.getPname())) {
            productVo.setPname("%" + productVo.getPname() + "%");
        }
        List<Product> ulist = productMapper.page(productVo);
        System.out.println(ulist);
        return new PageInfo<>(ulist);
    }

    @Override
    public void addp(Product product) {
        product.setPdate(new Date());
        productMapper.insert(product);
    }

    @Override
    public void del(Long id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Product searchbyid(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public void upde(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public List<Product> spall() {
      return productMapper.selectAll();
    }




}
