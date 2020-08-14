package com.czxy.service.impl;

import com.czxy.domain.Category;
import com.czxy.mapper.CategoryMapper;
import com.czxy.service.CategoryService;
import com.czxy.vo.CategoryVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
*
*@author zhandehuang@itcast.cn
*@date 2020/2/5 0005
*@version 1.0
**/

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> caselectall() {
        return categoryMapper.selectAll();
    }

    @Override
    public PageInfo<Category> page(CategoryVo categoryVo) {
        PageHelper.startPage(categoryVo.getPageNum(), categoryVo.getPageSize());
        if (StringUtils.isNotBlank(categoryVo.getCname())) {
            categoryVo.setCname("%" + categoryVo.getCname() + "%");
        }
        List<Category> ulist = categoryMapper.page(categoryVo);
        System.out.println(ulist);
        return new PageInfo<>(ulist);
    }

    @Override
    public void addp(Category category) {
        categoryMapper.insert(category);

    }

    @Override
    public void del(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category searchbyid(Long id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void upde(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}
