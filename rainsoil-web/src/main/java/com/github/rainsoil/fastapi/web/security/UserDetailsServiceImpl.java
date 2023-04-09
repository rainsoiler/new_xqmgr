package com.github.rainsoil.fastapi.web.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.rainsoil.fastapi.common.core.bean.BeanConvertUtils;
import com.github.rainsoil.fastapi.web.system.entity.SysMenu;
import com.github.rainsoil.fastapi.web.system.entity.SysRole;
import com.github.rainsoil.fastapi.web.system.entity.SysUser;
import com.github.rainsoil.fastapi.web.system.service.ISysMenuService;
import com.github.rainsoil.fastapi.web.system.service.ISysRoleService;
import com.github.rainsoil.fastapi.web.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息查询实现类
 *
 * @author luyanan
 * @since 2023/04/09
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


	private final ISysUserService sysUserService;

	private final ISysMenuService sysMenuService;

	private final ISysRoleService sysRoleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
		if (null == sysUser) {
			throw new UsernameNotFoundException(username + "没有找到");
		}

//		// 获取角色
//		SysRole sysRole = sysRoleService.getById(sysUser.getRoleId());
//		List<String> menuIds = Arrays.stream(sysRole.getMenus().split(",")).collect(Collectors.toList());
//		List<SysMenu> menuList = sysMenuService.listByIds(menuIds);

		return BeanConvertUtils.convertTo(sysUser, LoginUser::new);
	}
}
