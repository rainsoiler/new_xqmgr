package com.github.rainsoil.fastapi.web.system.controller;

import cn.hutool.core.util.StrUtil;
import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.mybatis.PageHandler;
import com.github.rainsoil.fastapi.web.security.LoginService;
import com.github.rainsoil.fastapi.web.security.LoginUser;
import com.github.rainsoil.fastapi.web.system.entity.SysMenu;
import com.github.rainsoil.fastapi.web.system.entity.SysRole;
import com.github.rainsoil.fastapi.web.system.service.ISysMenuService;
import com.github.rainsoil.fastapi.web.system.service.ISysRoleService;
import com.github.rainsoil.fastapi.web.system.vo.SysMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单表 前端控制器
 *
 * @author luyanan
 * @since 2023-04-09
 */
@Api(tags = "菜单表")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/sysMenu")
public class SysMenuController {

	private final ISysMenuService iSysMenuService;

	private final LoginService loginService;

	private final ISysRoleService sysRoleService;

	/**
	 * 分页
	 *
	 * @param pageRequest 分页参数
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.entity.SysMenu>>
	 * @since 2023/04/08
	 */
	@ApiOperation(value = "分页")
	@PostMapping("page")
	public R<PageInfo<SysMenu>> page(@RequestBody PageRequest<SysMenu> pageRequest) {
		PageInfo<SysMenu> pageInfo = iSysMenuService.page(pageRequest, new PageHandler<SysMenu>() {


		});
		return R.ok(pageInfo);
	}

	/**
	 * 保存
	 *
	 * @param sysMenu 实体类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@PostMapping("")
	@ApiOperation(value = "保存")
	public R save(@RequestBody SysMenu sysMenu) {

		this.iSysMenuService.save(sysMenu);
		return R.ok();
	}

	/**
	 * 修改
	 *
	 * @param sysMenu 实体类
	 * @return com.github.rainsoil.fastapi.common.core.R
	 * @since 2023-04-08
	 */
	@ApiOperation(value = "修改")
	@PutMapping("")
	public R update(@RequestBody SysMenu sysMenu) {
		this.iSysMenuService.updateById(sysMenu);
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
		this.iSysMenuService.removeById(id);
		return R.ok();
	}

	/**
	 * 查询当前用户的菜单
	 *
	 * @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.web.system.vo.SysMenuVo>
	 * @since 2023/04/09
	 */
	@ApiOperation(value = "查询当前用户的菜单")
	@GetMapping()
	public R<SysMenuVo> menu() {
		LoginUser user = loginService.getUser();
		String roleId = user.getRoleId();

		SysRole sysRole = sysRoleService.getById(roleId);
		String menus = sysRole.getMenus();
		List<SysMenu> sysMenus = this.iSysMenuService.listByIds(Arrays.stream(menus.split(",")).collect(Collectors.toList()));

		return R.ok(new SysMenuVo()
				.setMenus(sysMenus)
				.setPerms(sysMenus.stream().filter(a -> a.getType().equals("2") && StrUtil.isNotBlank(a.getPerms())).map(a -> a.getPerms()).collect(Collectors.toList()))
		);
	}

}
