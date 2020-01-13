package org.jeecg.modules.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.jeecg.modules.shop.entity.Commodity;
import org.jeecg.modules.shop.entity.SalesCommodity;
import org.jeecg.modules.shop.mapper.SalesCommodityMapper;
import org.jeecg.modules.shop.service.ICommodityService;
import org.jeecg.modules.shop.service.ISalesCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 销售单商品关联表
 * @Author: jeecg-boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
@Service
public class SalesCommodityServiceImpl extends ServiceImpl<SalesCommodityMapper, SalesCommodity> implements ISalesCommodityService {
    @Autowired
    private ICommodityService commodityService;

    /**
     * 获取销售单商品详细信息
     * @param queryWrapper
     * @return
     */
    @Override
    public List<SalesCommodity> getList(Wrapper<SalesCommodity> queryWrapper) {
        List<SalesCommodity> salesCommodities = this.baseMapper.selectList(queryWrapper);
        for (SalesCommodity salesCommodity : salesCommodities) {
            //关联查询商品信息
            Commodity commodity = commodityService.getById(salesCommodity.getCommodityId());
            salesCommodity.setCommodity(commodity);
        }
        return salesCommodities;
    }
}
