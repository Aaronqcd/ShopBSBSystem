package org.jeecg.modules.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.modules.shop.entity.Sales;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.shop.model.SaleStatModel;

import java.util.List;

/**
 * @Description: 销售单
 * @Author: jeecg-boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
public interface ISalesService extends IService<Sales> {
    /**
     * 销售统计
     * @return
     */
    List<SaleStatModel> saleStat();
}
