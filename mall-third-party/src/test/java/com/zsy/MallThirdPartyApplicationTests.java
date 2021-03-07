package com.zsy;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class MallThirdPartyApplicationTests {

    @Autowired
    OSSClient ossClient;

    @Test
    void contextLoads() throws FileNotFoundException {
        ossClient.putObject("gulimall-hello", "hh.png", new FileInputStream("C:\\Users\\ZSY\\Pictures\\dubhe.png"));
    }

}
