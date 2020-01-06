package org.jeecg.modules.shop.service.impl;

import org.jeecg.modules.shop.entity.Customer;
import org.jeecg.modules.shop.mapper.CustomerMapper;
import org.jeecg.modules.shop.service.ICustomerService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户管理
 * @Author: jeecg-boot
 * @Date:   2020-01-01
 * @Version: V1.0
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
