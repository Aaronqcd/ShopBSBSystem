package org.jeecg.modules.shop.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.shop.entity.Sales;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.shop.model.SaleStatModel;

/**
 * @Description: 销售单
 * @Author: jeecg-boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
public interface SalesMapper extends BaseMapper<Sales> {
    /**
     * 销售统计
     * @return
     */
    List<SaleStatModel> saleStat();
}
