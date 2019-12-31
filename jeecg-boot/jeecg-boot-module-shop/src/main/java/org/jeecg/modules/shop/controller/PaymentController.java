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
import org.jeecg.modules.shop.entity.Payment;
import org.jeecg.modules.shop.service.IPaymentService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.util.ToolsUtil;
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
 * @Description: 付款单
 * @Author: jeecg-boot
 * @Date:   2019-12-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="付款单")
@RestController
@RequestMapping("/payment")
public class PaymentController extends JeecgController<Payment, IPaymentService> {
	@Autowired
	private IPaymentService paymentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param payment
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "付款单-分页列表查询")
	@ApiOperation(value="付款单-分页列表查询", notes="付款单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Payment payment,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Payment> queryWrapper = QueryGenerator.initQueryWrapper(payment, req.getParameterMap());
		Page<Payment> page = new Page<Payment>(pageNo, pageSize);
		IPage<Payment> pageList = paymentService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param payment
	 * @return
	 */
	@AutoLog(value = "付款单-添加")
	@ApiOperation(value="付款单-添加", notes="付款单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Payment payment) {
		paymentService.save(payment);
		return Result.ok("添加成功！");
	}

	 /**
	  * 生成10位纯数字的付款单编号
	  * @return
	  */
	 @AutoLog(value = "付款单-生成付款单编号")
	 @ApiOperation(value="付款单-生成付款单编号", notes="付款单-生成付款单编号")
	 @GetMapping(value = "/getPaymentNo")
	 public Result<?> getPaymentNo() {
		 String paymentNo;
		 QueryWrapper queryWrapper;
		 Payment payment;
		 //保证付款单编号不重复
		 do {
			 paymentNo = ToolsUtil.getRandomNo(10);
			 queryWrapper = new QueryWrapper();
			 queryWrapper.eq("payment_no", paymentNo);
			 payment = paymentService.getOne(queryWrapper);
		 } while(payment != null);
		 return Result.ok(paymentNo);
	 }
	
	/**
	 * 编辑
	 *
	 * @param payment
	 * @return
	 */
	@AutoLog(value = "付款单-编辑")
	@ApiOperation(value="付款单-编辑", notes="付款单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Payment payment) {
		paymentService.updateById(payment);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "付款单-通过id删除")
	@ApiOperation(value="付款单-通过id删除", notes="付款单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		paymentService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "付款单-批量删除")
	@ApiOperation(value="付款单-批量删除", notes="付款单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.paymentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "付款单-通过id查询")
	@ApiOperation(value="付款单-通过id查询", notes="付款单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Payment payment = paymentService.getById(id);
		return Result.ok(payment);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param payment
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Payment payment) {
      return super.exportXls(request, payment, Payment.class, "付款单");
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
      return super.importExcel(request, response, Payment.class);
  }

}
