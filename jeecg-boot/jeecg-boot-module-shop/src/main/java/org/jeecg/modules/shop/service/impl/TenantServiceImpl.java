package org.jeecg.modules.shop.service.impl;

import org.jeecg.modules.shop.entity.Tenant;
import org.jeecg.modules.shop.mapper.TenantMapper;
import org.jeecg.modules.shop.service.ITenantService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 承租人
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

}
