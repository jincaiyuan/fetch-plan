package com.walker.fetchplan;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "dataSource", groupId = "rxy_fetch", autoRefreshed = true)
@NacosPropertySource(dataId = "config", groupId = "rxy_fetch", autoRefreshed = true)
public class FetchPlanApplication {

    public static void main(String[] args) {
        SpringApplication.run(FetchPlanApplication.class, args);
    }

}
