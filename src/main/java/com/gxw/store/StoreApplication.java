package com.gxw.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan({"com.gxw.store.project.user.mapper", "com.gxw.store.project.product.mapper", "com.gxw.store.project.order.mapper", "com.gxw.store.project.sale.mapper","com.gxw.store.project.common.mapper"})
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
