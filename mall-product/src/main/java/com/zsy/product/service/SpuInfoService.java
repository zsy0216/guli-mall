package com.zsy.product.service;

import com.zsy.product.entity.SpuInfoDescEntity;
import com.zsy.product.vo.SpuSaveVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsy.common.utils.PageUtils;
import com.zsy.product.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author zsy
 * @email 594983498@qq.com
 * @date 2019-10-01 21:08:49
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增商品信息
     *
     * @param vo
     */
    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity infoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    /**
     * 商品上架功能
     *
     * @param spuId
     */
    void up(Long spuId);
}

