package org.jeecg.modules.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.modules.shop.entity.Sales;
import org.jeecg.modules.shop.mapper.SalesMapper;
import org.jeecg.modules.shop.model.SaleStatModel;
import org.jeecg.modules.shop.service.ISalesService;
import org.jeecg.modules.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 销售单
 * @Author: jeecg-boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements ISalesService {
    @Autowired
    private SalesMapper salesMapper;

    /**
     * 销售统计
     * @return
     */
    @Override
    public List<SaleStatModel> saleStat() {
        List<SaleStatModel> saleStatModels = salesMapper.saleStat();
        return saleStatModels;
    }
}
