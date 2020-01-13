package org.jeecg.modules.shop.model;

import lombok.Data;
import org.jeecg.modules.shop.entity.Tenant;
import org.jeecg.modules.shop.entity.TenantShop;
import org.jeecg.modules.system.entity.SysUser;

import java.util.List;

@Data
public class TenantModel {
    private Tenant tenant;
    private SysUser user;
    private List<TenantShop> tenantShopList;
}
