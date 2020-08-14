package com.czxy.feign;

import com.czxy.domain.Orderitem;
import com.czxy.domain.Product;
import com.czxy.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/3/5 0005
 **/
@FeignClient(value = "productservice",path = "/product")
public interface OFeign {
    @GetMapping("/searchbyid/{id}")
    public BaseResult<Product> searchbyid(@PathVariable("id") Long id);

    @GetMapping("/{id}")
    public BaseResult findorid(@PathVariable("id") Long id);


    @PostMapping("/addorderitem")
    public BaseResult addorderitem(@RequestBody Orderitem orderitem);

    @DeleteMapping("/delos/{id}")
    public BaseResult delos(@PathVariable("id") Long id);
}
