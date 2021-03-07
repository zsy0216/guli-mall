package com.zsy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZSY
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallThirdPartyApplication.class, args);
    }

}
