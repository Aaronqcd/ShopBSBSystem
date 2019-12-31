package org.jeecg.modules.shop.service.impl;

import org.jeecg.modules.shop.entity.Payment;
import org.jeecg.modules.shop.mapper.PaymentMapper;
import org.jeecg.modules.shop.service.IPaymentService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 付款单
 * @Author: jeecg-boot
 * @Date:   2019-12-31
 * @Version: V1.0
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {

}
