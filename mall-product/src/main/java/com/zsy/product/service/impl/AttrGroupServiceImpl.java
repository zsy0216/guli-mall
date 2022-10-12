package com.zsy.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsy.product.entity.AttrEntity;
import com.zsy.product.service.AttrService;
import com.zsy.product.vo.AttrGroupWithAttrsVo;
import com.zsy.product.vo.SpuItemAttrGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsy.common.utils.PageUtils;
import com.zsy.common.utils.Query;

import com.zsy.product.dao.AttrGroupDao;
import com.zsy.product.entity.AttrGroupEntity;
import com.zsy.product.service.AttrGroupService;
import org.springframework.util.StringUtils;

/**
 * @author ZSY
 */
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {

        // 解析map，获得参数
        String key = (String) params.get("key");
        String pageSize = (String) params.get("page");
        String limitSize = (String) params.get("limit");
        String order = (String) params.get("order") != null ? (String) params.get("order") : "asc";
        String sidx = (String) params.get("sidx") != null ? (String) params.get("sidx") : "attr_group_id";
        boolean isAsc = "ASC".equals(order.toUpperCase());

        // 构造查询sql
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(key)) {
            wrapper.and(obj -> obj.eq("attr_group_id", key).or().like("attr_group_name", key)).orderBy(true, isAsc, sidx);
        }

        if (catelogId != 0) {
            wrapper.eq("catelog_id", catelogId);
        }
        Page<AttrGroupEntity> page = this.page(new Page<>(Long.parseLong(pageSize), Long.parseLong(limitSize)), wrapper);

        return new PageUtils(page);

    }

    /**
     * 根据分类id查出所有的分组以及这些组里面的属性
     *
     * @param catelogId
     * @return
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        //com.zsy.product.vo
        //1、查询分组信息
        List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));

        //2、查询所有属性
        return attrGroupEntities.stream().map(group -> {
            AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group, attrsVo);
            List<AttrEntity> attrs = attrService.getRelationAttr(attrsVo.getAttrGroupId());
            attrsVo.setAttrs(attrs);
            return attrsVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {

        //1、查出当前spu对应的所有属性的分组信息以及当前分组下的所有属性对应的值
        AttrGroupDao baseMapper = this.getBaseMapper();
        return baseMapper.getAttrGroupWithAttrsBySpuId(spuId, catalogId);
    }
}
