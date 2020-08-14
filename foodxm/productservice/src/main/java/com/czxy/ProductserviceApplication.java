package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/2/5 0005
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.czxy.mapper")
public class ProductserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class,args);
    }
}
