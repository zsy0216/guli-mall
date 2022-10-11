package com.zsy;

import com.zsy.product.entity.BrandEntity;
import com.zsy.product.entity.CategoryEntity;
import com.zsy.product.service.BrandService;
import com.zsy.product.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Autowired
    private CategoryService categoryService;

//    @Test
//    void contextLoads() {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setName("HuaWei");
//        brandService.save(brandEntity);
//        System.out.println("save successfully!");
//    }

    @Test
    void testListWithLambda(){

        List<CategoryEntity> entities1 = categoryService.listWithTree();
        List<CategoryEntity> entities = categoryService.listWithLambda();

        
    }



}
