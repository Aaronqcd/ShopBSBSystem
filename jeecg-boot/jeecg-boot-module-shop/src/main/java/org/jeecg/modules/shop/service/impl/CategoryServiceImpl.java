package org.jeecg.modules.shop.service.impl;

import org.jeecg.modules.shop.entity.Category;
import org.jeecg.modules.shop.mapper.CategoryMapper;
import org.jeecg.modules.shop.service.ICategoryService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 货品类别
 * @Author: jeecg-boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
