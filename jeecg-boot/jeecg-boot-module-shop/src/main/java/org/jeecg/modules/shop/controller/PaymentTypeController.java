package org.jeecg.modules.shop.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.shop.entity.PaymentType;
import org.jeecg.modules.shop.service.IPaymentTypeService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支付类型
 * @Author: aaron
 * @Date:   2019-12-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支付类型")
@RestController
@RequestMapping("/paymentType")
public class PaymentTypeController extends JeecgController<PaymentType, IPaymentTypeService> {
	@Autowired
	private IPaymentTypeService paymentTypeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param paymentType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支付类型-分页列表查询")
	@ApiOperation(value="支付类型-分页列表查询", notes="支付类型-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PaymentType paymentType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PaymentType> queryWrapper = QueryGenerator.initQueryWrapper(paymentType, req.getParameterMap());
		Page<PaymentType> page = new Page<PaymentType>(pageNo, pageSize);
		IPage<PaymentType> pageList = paymentTypeService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param paymentType
	 * @return
	 */
	@AutoLog(value = "支付类型-添加")
	@ApiOperation(value="支付类型-添加", notes="支付类型-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PaymentType paymentType) {
		paymentTypeService.save(paymentType);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param paymentType
	 * @return
	 */
	@AutoLog(value = "支付类型-编辑")
	@ApiOperation(value="支付类型-编辑", notes="支付类型-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PaymentType paymentType) {
		paymentTypeService.updateById(paymentType);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支付类型-通过id删除")
	@ApiOperation(value="支付类型-通过id删除", notes="支付类型-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		paymentTypeService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支付类型-批量删除")
	@ApiOperation(value="支付类型-批量删除", notes="支付类型-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.paymentTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支付类型-通过id查询")
	@ApiOperation(value="支付类型-通过id查询", notes="支付类型-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PaymentType paymentType = paymentTypeService.getById(id);
		return Result.ok(paymentType);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param paymentType
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PaymentType paymentType) {
      return super.exportXls(request, paymentType, PaymentType.class, "支付类型");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, PaymentType.class);
  }

}
