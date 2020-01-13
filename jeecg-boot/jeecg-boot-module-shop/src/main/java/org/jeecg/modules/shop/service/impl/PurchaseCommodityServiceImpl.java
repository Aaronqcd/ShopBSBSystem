package org.jeecg.modules.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.jeecg.modules.shop.entity.Commodity;
import org.jeecg.modules.shop.entity.PurchaseCommodity;
import org.jeecg.modules.shop.mapper.PurchaseCommodityMapper;
import org.jeecg.modules.shop.service.ICommodityService;
import org.jeecg.modules.shop.service.IPurchaseCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 进货单商品关联表
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Service
public class PurchaseCommodityServiceImpl extends ServiceImpl<PurchaseCommodityMapper, PurchaseCommodity> implements IPurchaseCommodityService {
    @Autowired
    private ICommodityService commodityService;

    /**
     * 获取进货单商品详细信息
     * @param queryWrapper
     * @return
     */
    @Override
    public List<PurchaseCommodity> getList(Wrapper<PurchaseCommodity> queryWrapper) {
        List<PurchaseCommodity> purchaseCommodityList = this.baseMapper.selectList(queryWrapper);
        for (PurchaseCommodity purchaseCommodity : purchaseCommodityList) {
            //关联查询商品信息
            Commodity commodity = commodityService.getById(purchaseCommodity.getCommodityId());
            purchaseCommodity.setCommodity(commodity);
        }
        return purchaseCommodityList;
    }
}
