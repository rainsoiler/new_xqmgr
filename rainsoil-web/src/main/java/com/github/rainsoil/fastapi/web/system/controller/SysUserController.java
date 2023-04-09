package com.github.rainsoil.fastapi.web.system.controller;

import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.mybatis.PageHandler;
import com.github.rainsoil.fastapi.web.security.LoginService;
import com.github.rainsoil.fastapi.web.system.entity.SysUser;
import com.github.rainsoil.fastapi.web.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户表 前端控制器
 *
 * @author luyanan
 * @since 2023-04-09
 */
@Api(tags = "系统用户表")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/sysUser")
public class SysUserController {

	private final ISysUserService iSysUserService;

	private final LoginService loginService;

	/**
	 * 分页
	 *
	 * @param pageRequest 分页参数
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.entity.SysUser>>
	 * @since 2023/04/08
	 */
	@ApiOperation(value = "分页")
	@PostMapping("page")
	public R<PageInfo<SysUser>> page(@RequestBody PageRequest<SysUser> pageRequest) {
		PageInfo<SysUser> pageInfo = iSysUserService.page(pageRequest, new PageHandler<SysUser>() {


		});
		return R.ok(pageInfo);
	}

	/**
	 * 保存
	 *
	 * @param sysUser 实体类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@PostMapping("")
	@ApiOperation(value = "保存")
	public R save(@RequestBody SysUser sysUser) {

		this.iSysUserService.save(sysUser);
		return R.ok();
	}

	/**
	 * 修改
	 *
	 * @param sysUser 实体类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@ApiOperation(value = "修改")
	@PutMapping("")
	public R update(@RequestBody SysUser sysUser) {
		this.iSysUserService.updateById(sysUser);
		return R.ok();
	}

	/**
	 * 根据id删除
	 *
	 * @param id id
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@DeleteMapping()
	@ApiOperation(value = "根据id删除")
	public R remove(@RequestParam(value = "id", required = true) Long id) {
		this.iSysUserService.removeById(id);
		return R.ok();
	}

	/**
	 * 获取当前登录人的信息
	 *
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023/04/09
	 */
	@ApiOperation(value = "获取当前登录人的信息")
	@GetMapping("info")
	public R<SysUser> info() {

		SysUser sysUser = this.iSysUserService.getById(loginService.getUser().getId());
		sysUser.setPassword(null);
		return R.ok(sysUser);
	}
}
