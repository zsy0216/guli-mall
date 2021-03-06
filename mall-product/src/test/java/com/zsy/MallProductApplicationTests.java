package com.zsy;

import com.zsy.product.entity.BrandEntity;
import com.zsy.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("HuaWei");
        brandService.save(brandEntity);
        System.out.println("save successfully!");
    }

}
