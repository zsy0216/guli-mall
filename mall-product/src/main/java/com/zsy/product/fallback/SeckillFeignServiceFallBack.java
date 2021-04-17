package com.zsy.product.fallback;

import com.zsy.common.exception.BizCodeEnum;
import com.zsy.common.utils.R;
import com.zsy.product.feign.SeckillFeignService;
import org.springframework.stereotype.Component;

/**
 * @author zsy
 * @email 594983498@qq.com
 * @date 2019-10-01 21:08:49
 **/
@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckilInfo(Long skuId) {
        return R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMsg());
    }
}
