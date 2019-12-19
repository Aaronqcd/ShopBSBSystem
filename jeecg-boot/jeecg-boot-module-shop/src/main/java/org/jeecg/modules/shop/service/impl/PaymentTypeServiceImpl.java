package org.jeecg.modules.shop.service.impl;

import org.jeecg.modules.shop.entity.PaymentType;
import org.jeecg.modules.shop.mapper.PaymentTypeMapper;
import org.jeecg.modules.shop.service.IPaymentTypeService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 支付类型
 * @Author: jeecg-boot
 * @Date:   2019-12-16
 * @Version: V1.0
 */
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType> implements IPaymentTypeService {

}
