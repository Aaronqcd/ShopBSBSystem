package org.jeecg.modules.shop.controller;

import java.math.BigDecimal;
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
import org.jeecg.modules.shop.entity.Commodity;
import org.jeecg.modules.shop.entity.Sales;
import org.jeecg.modules.shop.entity.SalesCommodity;
import org.jeecg.modules.shop.model.SalesModel;
import org.jeecg.modules.shop.service.ICommodityService;
import org.jeecg.modules.shop.service.ISalesCommodityService;
import org.jeecg.modules.shop.service.ISalesService;
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
 * @Description: 销售单
 * @Author: jeecg-boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="销售单")
@RestController
@RequestMapping("/sales")
public class SalesController extends JeecgController<Sales, ISalesService> {
	@Autowired
	private ISalesService salesService;
	 @Autowired
	 private ICommodityService commodityService;
	 @Autowired
	 private ISalesCommodityService salesCommodityService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sales
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "销售单-分页列表查询")
	@ApiOperation(value="销售单-分页列表查询", notes="销售单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Sales sales,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Sales> queryWrapper = QueryGenerator.initQueryWrapper(sales, req.getParameterMap());
		Page<Sales> page = new Page<Sales>(pageNo, pageSize);
		IPage<Sales> pageList = salesService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param salesModel
	 * @return
	 */
	@AutoLog(value = "销售单-添加")
	@ApiOperation(value="销售单-添加", notes="销售单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SalesModel salesModel) {
		Sales sales = salesModel.getSales();
		sales.setSettlementAccountId("1");//结算账户先写死
		sales.setState("2");//状态，1表示未保存，2表示已保存
		salesService.save(sales);
		List<SalesCommodity> commodityList = salesModel.getCommodityList();
		//保存销售单商品关联表
		for (SalesCommodity salesCommodity : commodityList) {
			salesCommodity.setSalesId(sales.getId());
			salesCommodityService.save(salesCommodity);
		}
		return Result.ok("添加成功！");
	}

	 /**
	  * 生成10位纯数字的销售单编号
	  * @return
	  */
	 @AutoLog(value = "销售单-生成销售单编号")
	 @ApiOperation(value="销售单-生成销售单编号", notes="销售单-生成销售单编号")
	 @GetMapping(value = "/getSalesNo")
	 public Result<?> getSalesNo() {
		String salesNo;
		QueryWrapper queryWrapper;
		Sales sales;
		//保证销售单编号不能重复
		do {
			salesNo = ToolsUtil.getRandomNo(10);
			queryWrapper = new QueryWrapper();
			queryWrapper.eq("sales_no", salesNo);
			sales = salesService.getOne(queryWrapper);
		} while(sales != null);
		return Result.ok(salesNo);
	 }

	 /**
	  * 模拟扫描枪扫描商品的功能
	  * @return
	  */
	 @AutoLog(value = "销售单-模拟扫描枪功能")
	 @ApiOperation(value="销售单-模拟扫描枪功能", notes="销售单-模拟扫描枪功能")
	 @GetMapping(value = "/getSalesCommodity")
	 public Result<?> getSalesCommodity() {
		 String commodityNo;
		 QueryWrapper queryWrapper;
		 Commodity commodity;
		 do {
			 commodityNo = ToolsUtil.getRandomNo(5);
			 queryWrapper = new QueryWrapper();
			 queryWrapper.eq("commodity_no", commodityNo);
			 commodity = commodityService.getOne(queryWrapper);
		 } while(commodity != null);
		 commodity = new Commodity();
		 commodity.setName("商品"+commodityNo);
		 commodity.setCategoryId("1");//货品类别先写死
		 commodity.setUomId("1");//商品单位先写死
		 BigDecimal price = ToolsUtil.getRandomPrice();
		 //采购价=单价*0.8
		 BigDecimal purchasePrice = ToolsUtil.getFormatPrice(price.multiply(new BigDecimal(0.8)));
		 //批发价=单价*1.2
		 BigDecimal tradePrice = ToolsUtil.getFormatPrice(price.multiply(new BigDecimal(1.2)));
		 commodity.setPrice(price);
		 commodity.setPurchasePrice(purchasePrice);
		 commodity.setTradePrice(tradePrice);
		 commodityService.save(commodity);
		 return Result.ok(commodity);
	 }
	
	/**
	 * 编辑
	 *
	 * @param sales
	 * @return
	 */
	@AutoLog(value = "销售单-编辑")
	@ApiOperation(value="销售单-编辑", notes="销售单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Sales sales) {
		salesService.updateById(sales);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "销售单-通过id删除")
	@ApiOperation(value="销售单-通过id删除", notes="销售单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		salesService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "销售单-批量删除")
	@ApiOperation(value="销售单-批量删除", notes="销售单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.salesService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "销售单-通过id查询")
	@ApiOperation(value="销售单-通过id查询", notes="销售单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Sales sales = salesService.getById(id);
		return Result.ok(sales);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sales
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Sales sales) {
      return super.exportXls(request, sales, Sales.class, "销售单");
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
      return super.importExcel(request, response, Sales.class);
  }

}
