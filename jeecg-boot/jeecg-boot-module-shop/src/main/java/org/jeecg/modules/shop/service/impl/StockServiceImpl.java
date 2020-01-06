package org.jeecg.modules.shop.service.impl;

import org.jeecg.modules.shop.entity.Stock;
import org.jeecg.modules.shop.mapper.StockMapper;
import org.jeecg.modules.shop.service.IStockService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 货品库存
 * @Author: jeecg-boot
 * @Date:   2020-01-01
 * @Version: V1.0
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {
}
