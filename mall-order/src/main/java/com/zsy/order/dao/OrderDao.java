package com.zsy.order.dao;

import com.zsy.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 *
 * @author zsy
 * @email 594983498@qq.com
 * @date 2019-10-08 09:56:16
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

}
