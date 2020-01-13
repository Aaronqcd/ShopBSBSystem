package org.jeecg.modules.shop.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.jeecg.modules.shop.entity.SalesCommodity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 销售单商品关联表
 * @Author: jeecg-boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
public interface ISalesCommodityService extends IService<SalesCommodity> {
    /**
     * 获取销售单商品详细信息
     * @param queryWrapper
     * @return
     */
    List<SalesCommodity> getList(Wrapper<SalesCommodity> queryWrapper);
}
