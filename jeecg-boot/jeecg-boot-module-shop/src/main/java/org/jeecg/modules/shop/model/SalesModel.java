package org.jeecg.modules.shop.model;

import lombok.Data;
import org.jeecg.modules.shop.entity.Sales;
import org.jeecg.modules.shop.entity.SalesCommodity;

import java.util.List;

@Data
public class SalesModel {
    private Sales sales;
    private List<SalesCommodity> commodityList;
}
