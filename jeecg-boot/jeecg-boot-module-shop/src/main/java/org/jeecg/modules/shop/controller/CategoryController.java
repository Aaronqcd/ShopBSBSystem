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
import org.jeecg.modules.shop.entity.Category;
import org.jeecg.modules.shop.service.ICategoryService;
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
 * @Description: 货品类别
 * @Author: jeecg-boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="货品类别")
@RestController
@RequestMapping("/category")
public class CategoryController extends JeecgController<Category, ICategoryService> {
	@Autowired
	private ICategoryService categoryService;
	
	/**
	 * 分页列表查询
	 *
	 * @param category
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "货品类别-分页列表查询")
	@ApiOperation(value="货品类别-分页列表查询", notes="货品类别-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Category category,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Category> queryWrapper = QueryGenerator.initQueryWrapper(category, req.getParameterMap());
		Page<Category> page = new Page<Category>(pageNo, pageSize);
		IPage<Category> pageList = categoryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param category
	 * @return
	 */
	@AutoLog(value = "货品类别-添加")
	@ApiOperation(value="货品类别-添加", notes="货品类别-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Category category) {
		categoryService.save(category);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param category
	 * @return
	 */
	@AutoLog(value = "货品类别-编辑")
	@ApiOperation(value="货品类别-编辑", notes="货品类别-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Category category) {
		categoryService.updateById(category);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "货品类别-通过id删除")
	@ApiOperation(value="货品类别-通过id删除", notes="货品类别-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		categoryService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "货品类别-批量删除")
	@ApiOperation(value="货品类别-批量删除", notes="货品类别-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.categoryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "货品类别-通过id查询")
	@ApiOperation(value="货品类别-通过id查询", notes="货品类别-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Category category = categoryService.getById(id);
		return Result.ok(category);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param category
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Category category) {
      return super.exportXls(request, category, Category.class, "货品类别");
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
      return super.importExcel(request, response, Category.class);
  }

}
