package org.jeecg.modules.shop.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.shop.entity.Commodity;
import org.jeecg.modules.shop.entity.Stock;
import org.jeecg.modules.shop.service.ICommodityService;
import org.jeecg.modules.shop.service.IStockService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 货品库存
 * @Author: jeecg-boot
 * @Date:   2020-01-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="货品库存")
@RestController
@RequestMapping("/stock")
public class StockController extends JeecgController<Stock, IStockService> {
	@Autowired
	private IStockService stockService;
	 @Autowired
	 private ICommodityService commodityService;

	/**
	 * 分页列表查询
	 *
	 * @param stock
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "货品库存-分页列表查询")
	@ApiOperation(value="货品库存-分页列表查询", notes="货品库存-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Stock stock,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Stock> queryWrapper = QueryGenerator.initQueryWrapper(stock, req.getParameterMap());
		Page<Stock> page = new Page<Stock>(pageNo, pageSize);
		IPage<Stock> pageList = stockService.page(page, queryWrapper);
		for (Stock stockObj : pageList.getRecords()) {
			Commodity commodity = commodityService.getById(stockObj.getCommodityId());
			stockObj.setCommodity(commodity);
		}
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param stock
	 * @return
	 */
	@AutoLog(value = "货品库存-添加")
	@ApiOperation(value="货品库存-添加", notes="货品库存-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Stock stock) {
		stockService.save(stock);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param stock
	 * @return
	 */
	@AutoLog(value = "货品库存-编辑")
	@ApiOperation(value="货品库存-编辑", notes="货品库存-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Stock stock) {
		stockService.updateById(stock);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "货品库存-通过id删除")
	@ApiOperation(value="货品库存-通过id删除", notes="货品库存-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		stockService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "货品库存-批量删除")
	@ApiOperation(value="货品库存-批量删除", notes="货品库存-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.stockService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "货品库存-通过id查询")
	@ApiOperation(value="货品库存-通过id查询", notes="货品库存-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Stock stock = stockService.getById(id);
		return Result.ok(stock);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param stock
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Stock stock) {
      return super.exportXls(request, stock, Stock.class, "货品库存");
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
      return super.importExcel(request, response, Stock.class);
  }

}
