package com.czxy.service;


import com.czxy.domain.Category;
import com.czxy.vo.CategoryVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
*
*@author zhandehuang@itcast.cn
*@date 2020/2/5 0005
*@version 1.0
**/

public interface CategoryService{

    List<Category> caselectall();

    PageInfo<Category> page(CategoryVo categoryVo);

    void addp(Category category);

    void del(Long id);

    Category searchbyid(Long id);

    void upde(Category category);
}
