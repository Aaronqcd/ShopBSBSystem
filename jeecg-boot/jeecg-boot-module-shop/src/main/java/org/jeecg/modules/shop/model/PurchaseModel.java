package org.jeecg.modules.shop.model;

import lombok.Data;
import org.jeecg.modules.shop.entity.Purchase;
import org.jeecg.modules.shop.entity.PurchaseCommodity;
import org.jeecg.modules.shop.entity.Sales;
import org.jeecg.modules.shop.entity.SalesCommodity;

import java.util.List;

@Data
public class PurchaseModel {
    private Purchase purchase;
    private List<PurchaseCommodity> commodityList;
}
