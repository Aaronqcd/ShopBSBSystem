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
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.shop.entity.Assistant;
import org.jeecg.modules.shop.model.AssistantModel;
import org.jeecg.modules.shop.service.IAssistantService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.system.entity.SysRole;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysRoleService;
import org.jeecg.modules.system.service.ISysUserService;
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
 * @Description: 营业员
 * @Author: aaron
 * @Date:   2019-12-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="营业员")
@RestController
@RequestMapping("/assistant")
public class AssistantController extends JeecgController<Assistant, IAssistantService> {
	@Autowired
	private IAssistantService assistantService;
	 @Autowired
	 private ISysUserService sysUserService;
	 @Autowired
	 private ISysRoleService sysRoleService;
	
	/**
	 * 分页列表查询
	 *
	 * @param assistant
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "营业员-分页列表查询")
	@ApiOperation(value="营业员-分页列表查询", notes="营业员-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Assistant assistant,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Assistant> queryWrapper = QueryGenerator.initQueryWrapper(assistant, req.getParameterMap());
		Page<Assistant> page = new Page<Assistant>(pageNo, pageSize);
		IPage<Assistant> pageList = assistantService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param assistantModel
	 * @return
	 */
	@AutoLog(value = "营业员-添加")
	@ApiOperation(value="营业员-添加", notes="营业员-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AssistantModel assistantModel) {
		Assistant assistant = assistantModel.getAssistant();
		SysUser user = assistantModel.getUser();
		if(user == null) {
			user = new SysUser();
		}
		user.setCreateTime(new Date());//设置创建时间
		String salt = oConvertUtils.randomGen(8);
		user.setSalt(salt);
		String passwordEncode = PasswordUtil.encrypt(user.getUsername(), "123456", salt);
		user.setPassword(passwordEncode);
		user.setStatus(1);
		user.setDelFlag("0");
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("role_code", "assistant");
		SysRole sysRole = sysRoleService.getOne(queryWrapper);
		boolean flag = sysUserService.addUserAndRole(user, sysRole.getId());//营业员角色id
		if(!flag) {
			return Result.error("登录账号已存在！");
		}
		assistant.setUserId(user.getId());
		assistantService.save(assistant);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param assistant
	 * @return
	 */
	@AutoLog(value = "营业员-编辑")
	@ApiOperation(value="营业员-编辑", notes="营业员-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Assistant assistant) {
		assistantService.updateById(assistant);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "营业员-通过id删除")
	@ApiOperation(value="营业员-通过id删除", notes="营业员-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		assistantService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "营业员-批量删除")
	@ApiOperation(value="营业员-批量删除", notes="营业员-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.assistantService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "营业员-通过id查询")
	@ApiOperation(value="营业员-通过id查询", notes="营业员-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Assistant assistant = assistantService.getById(id);
		return Result.ok(assistant);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param assistant
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Assistant assistant) {
      return super.exportXls(request, assistant, Assistant.class, "营业员");
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
      return super.importExcel(request, response, Assistant.class);
  }

}
