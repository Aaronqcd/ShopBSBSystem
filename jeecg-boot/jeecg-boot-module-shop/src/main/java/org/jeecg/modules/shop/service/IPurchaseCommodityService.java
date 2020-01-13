package org.jeecg.modules.shop.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.jeecg.modules.shop.entity.PurchaseCommodity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 进货单商品关联表
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface IPurchaseCommodityService extends IService<PurchaseCommodity> {
    /**
     * 获取进货单商品详细信息
     * @param queryWrapper
     * @return
     */
    List<PurchaseCommodity> getList(Wrapper<PurchaseCommodity> queryWrapper);
}
