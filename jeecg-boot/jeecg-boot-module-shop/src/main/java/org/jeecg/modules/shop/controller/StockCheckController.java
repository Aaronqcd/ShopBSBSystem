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
import org.jeecg.modules.shop.entity.StockCheck;
import org.jeecg.modules.shop.service.IStockCheckService;
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
 * @Description: 库存盘点
 * @Author: jeecg-boot
 * @Date:   2020-01-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="库存盘点")
@RestController
@RequestMapping("/stockCheck")
public class StockCheckController extends JeecgController<StockCheck, IStockCheckService> {
	@Autowired
	private IStockCheckService stockCheckService;
	
	/**
	 * 分页列表查询
	 *
	 * @param stockCheck
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "库存盘点-分页列表查询")
	@ApiOperation(value="库存盘点-分页列表查询", notes="库存盘点-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(StockCheck stockCheck,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<StockCheck> queryWrapper = QueryGenerator.initQueryWrapper(stockCheck, req.getParameterMap());
		Page<StockCheck> page = new Page<StockCheck>(pageNo, pageSize);
		IPage<StockCheck> pageList = stockCheckService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param stockCheck
	 * @return
	 */
	@AutoLog(value = "库存盘点-添加")
	@ApiOperation(value="库存盘点-添加", notes="库存盘点-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody StockCheck stockCheck) {
		stockCheckService.save(stockCheck);
		return Result.ok("添加成功！");
	}

	 /**
	  * 生成10位纯数字的库存盘点单编号
	  * @return
	  */
	 @AutoLog(value = "库存盘点-生成库存盘点单编号")
	 @ApiOperation(value="库存盘点-生成库存盘点单编号", notes="库存盘点-生成库存盘点单编号")
	 @GetMapping(value = "/getStockCheckNo")
	 public Result<?> getStockCheckNo() {
		 String stockCheckNo;
		 QueryWrapper queryWrapper;
		 StockCheck stockCheck;
		 //保证库存盘点单编号不能重复
		 do {
			 stockCheckNo = ToolsUtil.getRandomNo(10);
			 queryWrapper = new QueryWrapper();
			 queryWrapper.eq("stock_check_no", stockCheckNo);
			 stockCheck = stockCheckService.getOne(queryWrapper);
		 } while(stockCheck != null);
		 return Result.ok(stockCheckNo);
	 }
	
	/**
	 * 编辑
	 *
	 * @param stockCheck
	 * @return
	 */
	@AutoLog(value = "库存盘点-编辑")
	@ApiOperation(value="库存盘点-编辑", notes="库存盘点-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody StockCheck stockCheck) {
		stockCheckService.updateById(stockCheck);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "库存盘点-通过id删除")
	@ApiOperation(value="库存盘点-通过id删除", notes="库存盘点-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		stockCheckService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "库存盘点-批量删除")
	@ApiOperation(value="库存盘点-批量删除", notes="库存盘点-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.stockCheckService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "库存盘点-通过id查询")
	@ApiOperation(value="库存盘点-通过id查询", notes="库存盘点-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		StockCheck stockCheck = stockCheckService.getById(id);
		return Result.ok(stockCheck);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param stockCheck
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, StockCheck stockCheck) {
      return super.exportXls(request, stockCheck, StockCheck.class, "库存盘点");
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
      return super.importExcel(request, response, StockCheck.class);
  }

}
