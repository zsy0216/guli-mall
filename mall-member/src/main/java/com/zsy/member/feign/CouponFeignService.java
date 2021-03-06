package com.zsy.member.feign;

import com.zsy.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这是一个声明式的远程调用
 * @author ZSY
 */
@FeignClient("mall-coupon")
public interface CouponFeignService {

    /**
     * 远程调用 coupon 测试
     * @return
     */
    @RequestMapping("/coupon/coupon/member/list")
    R memberCoupons();
}
