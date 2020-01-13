package org.jeecg.modules.shop.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.shop.entity.Tenant;
import org.jeecg.modules.shop.entity.TenantShop;
import org.jeecg.modules.shop.model.TenantModel;
import org.jeecg.modules.shop.service.ITenantService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.shop.service.ITenantShopService;
import org.jeecg.modules.system.entity.SysRole;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysRoleService;
import org.jeecg.modules.system.service.ISysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 承租人
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="承租人")
@RestController
@RequestMapping("/tenant")
public class TenantController extends JeecgController<Tenant, ITenantService> {
	@Autowired
	private ITenantService tenantService;
	 @Autowired
	 private ISysUserService sysUserService;
	 @Autowired
	 private ISysRoleService sysRoleService;
	 @Autowired
	 private ITenantShopService tenantShopService;

	/**
	 * 分页列表查询
	 *
	 * @param tenant
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "承租人-分页列表查询")
	@ApiOperation(value="承租人-分页列表查询", notes="承租人-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tenant tenant,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tenant> queryWrapper = QueryGenerator.initQueryWrapper(tenant, req.getParameterMap());
		Page<Tenant> page = new Page<Tenant>(pageNo, pageSize);
		IPage<Tenant> pageList = tenantService.page(page, queryWrapper);
		for (Tenant tenantObj : pageList.getRecords()) {
			//关联查询用户信息
			SysUser user = sysUserService.getById(tenantObj.getUserId());
			tenantObj.setUser(user);
			//关联查询承租人店铺信息
			QueryWrapper<TenantShop> salesQueryWrapper = new QueryWrapper();
			salesQueryWrapper.eq("tenant_id", tenantObj.getId());
			List<TenantShop> tenantShopList = tenantShopService.getList(salesQueryWrapper);
			tenantObj.setTenantShopList(tenantShopList);
		}
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tenantModel
	 * @return
	 */
	@AutoLog(value = "承租人-添加")
	@ApiOperation(value="承租人-添加", notes="承租人-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TenantModel tenantModel) {
		Tenant tenant = tenantModel.getTenant();
		SysUser user = tenantModel.getUser();
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
		queryWrapper.eq("role_code", "tenant");
		SysRole sysRole = sysRoleService.getOne(queryWrapper);
		boolean flag = sysUserService.addUserAndRole(user, sysRole.getId());//承租人角色id
		if(!flag) {
			return Result.error("登录账号已存在！");
		}
		tenant.setUserId(user.getId());
		tenantService.save(tenant);
		List<TenantShop> tenantShopList = tenantModel.getTenantShopList();
		//保存承租人店铺关联表
		for (TenantShop tenantShop : tenantShopList) {
			tenantShop.setTenantId(tenant.getId());
			tenantShopService.save(tenantShop);
		}
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tenant
	 * @return
	 */
	@AutoLog(value = "承租人-编辑")
	@ApiOperation(value="承租人-编辑", notes="承租人-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tenant tenant) {
		tenantService.updateById(tenant);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "承租人-通过id删除")
	@ApiOperation(value="承租人-通过id删除", notes="承租人-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tenantService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "承租人-批量删除")
	@ApiOperation(value="承租人-批量删除", notes="承租人-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tenantService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "承租人-通过id查询")
	@ApiOperation(value="承租人-通过id查询", notes="承租人-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tenant tenant = tenantService.getById(id);
		return Result.ok(tenant);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tenant
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Tenant tenant) {
      return super.exportXls(request, tenant, Tenant.class, "承租人");
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
      return super.importExcel(request, response, Tenant.class);
  }

}
