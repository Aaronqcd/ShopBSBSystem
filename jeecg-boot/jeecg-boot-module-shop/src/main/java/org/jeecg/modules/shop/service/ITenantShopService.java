package org.jeecg.modules.shop.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.jeecg.modules.shop.entity.TenantShop;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 承租人店铺关联
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface ITenantShopService extends IService<TenantShop> {
    /**
     * 获取承租人店铺详细信息
     * @param queryWrapper
     * @return
     */
    List<TenantShop> getList(Wrapper<TenantShop> queryWrapper);
}
