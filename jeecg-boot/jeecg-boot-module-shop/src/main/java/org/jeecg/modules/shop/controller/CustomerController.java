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
import org.jeecg.modules.shop.entity.Customer;
import org.jeecg.modules.shop.service.ICustomerService;
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
 * @Description: 客户管理
 * @Author: jeecg-boot
 * @Date:   2020-01-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户管理")
@RestController
@RequestMapping("/customer")
public class CustomerController extends JeecgController<Customer, ICustomerService> {
	@Autowired
	private ICustomerService customerService;
	
	/**
	 * 分页列表查询
	 *
	 * @param customer
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户管理-分页列表查询")
	@ApiOperation(value="客户管理-分页列表查询", notes="客户管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Customer customer,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Customer> queryWrapper = QueryGenerator.initQueryWrapper(customer, req.getParameterMap());
		Page<Customer> page = new Page<Customer>(pageNo, pageSize);
		IPage<Customer> pageList = customerService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param customer
	 * @return
	 */
	@AutoLog(value = "客户管理-添加")
	@ApiOperation(value="客户管理-添加", notes="客户管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Customer customer) {
		customerService.save(customer);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param customer
	 * @return
	 */
	@AutoLog(value = "客户管理-编辑")
	@ApiOperation(value="客户管理-编辑", notes="客户管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Customer customer) {
		customerService.updateById(customer);
		return Result.ok("编辑成功!");
	}

	 /**
	  * 通过id删除
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "客户管理-通过id删除")
	 @ApiOperation(value="客户管理-通过id删除", notes="客户管理-通过id删除")
	 @DeleteMapping(value = "/delete")
	 public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		 customerService.removeById(id);
		 return Result.ok("删除成功!");
	 }
	
	/**
	 * 修改客户状态，1表示启用，2表示停用
	 *
	 * @param id
	 * @param state
	 * @return
	 */
	@AutoLog(value = "客户管理-修改客户状态")
	@ApiOperation(value="客户管理-修改客户状态", notes="客户管理-修改客户状态")
	@PutMapping(value = "/changeState")
	public Result<?> changeState(@RequestParam(name="id",required=true) String id,
								 @RequestParam(name="state",required=true) String state) {
		Customer customer = customerService.getById(id);
		customer.setState(state);
		customerService.updateById(customer);
		return Result.ok("成功修改客户状态!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户管理-批量删除")
	@ApiOperation(value="客户管理-批量删除", notes="客户管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.customerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户管理-通过id查询")
	@ApiOperation(value="客户管理-通过id查询", notes="客户管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Customer customer = customerService.getById(id);
		return Result.ok(customer);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param customer
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Customer customer) {
      return super.exportXls(request, customer, Customer.class, "客户管理");
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
      return super.importExcel(request, response, Customer.class);
  }

}
