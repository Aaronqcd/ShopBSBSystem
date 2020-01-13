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
import org.jeecg.modules.shop.entity.TenantShop;
import org.jeecg.modules.shop.service.ITenantShopService;
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
 * @Description: 承租人店铺关联
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="承租人店铺关联")
@RestController
@RequestMapping("/tenantShop")
public class TenantShopController extends JeecgController<TenantShop, ITenantShopService> {
	@Autowired
	private ITenantShopService tenantShopService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tenantShop
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "承租人店铺关联-分页列表查询")
	@ApiOperation(value="承租人店铺关联-分页列表查询", notes="承租人店铺关联-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TenantShop tenantShop,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TenantShop> queryWrapper = QueryGenerator.initQueryWrapper(tenantShop, req.getParameterMap());
		Page<TenantShop> page = new Page<TenantShop>(pageNo, pageSize);
		IPage<TenantShop> pageList = tenantShopService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tenantShop
	 * @return
	 */
	@AutoLog(value = "承租人店铺关联-添加")
	@ApiOperation(value="承租人店铺关联-添加", notes="承租人店铺关联-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TenantShop tenantShop) {
		tenantShopService.save(tenantShop);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tenantShop
	 * @return
	 */
	@AutoLog(value = "承租人店铺关联-编辑")
	@ApiOperation(value="承租人店铺关联-编辑", notes="承租人店铺关联-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TenantShop tenantShop) {
		tenantShopService.updateById(tenantShop);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "承租人店铺关联-通过id删除")
	@ApiOperation(value="承租人店铺关联-通过id删除", notes="承租人店铺关联-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tenantShopService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "承租人店铺关联-批量删除")
	@ApiOperation(value="承租人店铺关联-批量删除", notes="承租人店铺关联-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tenantShopService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "承租人店铺关联-通过id查询")
	@ApiOperation(value="承租人店铺关联-通过id查询", notes="承租人店铺关联-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TenantShop tenantShop = tenantShopService.getById(id);
		return Result.ok(tenantShop);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tenantShop
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TenantShop tenantShop) {
      return super.exportXls(request, tenantShop, TenantShop.class, "承租人店铺关联");
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
      return super.importExcel(request, response, TenantShop.class);
  }

}
