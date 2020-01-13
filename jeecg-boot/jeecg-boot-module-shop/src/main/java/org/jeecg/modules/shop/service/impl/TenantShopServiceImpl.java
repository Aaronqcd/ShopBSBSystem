package org.jeecg.modules.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.jeecg.modules.shop.entity.Shop;
import org.jeecg.modules.shop.entity.TenantShop;
import org.jeecg.modules.shop.mapper.TenantShopMapper;
import org.jeecg.modules.shop.service.IShopService;
import org.jeecg.modules.shop.service.ITenantShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 承租人店铺关联
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Service
public class TenantShopServiceImpl extends ServiceImpl<TenantShopMapper, TenantShop> implements ITenantShopService {
    @Autowired
    private IShopService shopService;

    /**
     * 获取承租人店铺详细信息
     * @param queryWrapper
     * @return
     */
    @Override
    public List<TenantShop> getList(Wrapper<TenantShop> queryWrapper) {
        List<TenantShop> tenantShopList = this.baseMapper.selectList(queryWrapper);
        for (TenantShop tenantShop : tenantShopList) {
            //关联查询店铺信息
            Shop shop = shopService.getById(tenantShop.getShopId());
            tenantShop.setShop(shop);
        }
        return tenantShopList;
    }
}
