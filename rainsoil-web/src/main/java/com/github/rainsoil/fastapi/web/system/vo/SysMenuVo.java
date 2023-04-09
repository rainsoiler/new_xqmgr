package com.github.rainsoil.fastapi.web.system.vo;

import com.github.rainsoil.fastapi.web.system.entity.SysMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 菜单vo
 *
 * @author luyanan
 * @since 2023/04/09
 */
@Accessors(chain = true)
@Data
public class SysMenuVo {

	/**
	 * 菜单列表
	 *
	 * @since 2023/04/09
	 */
	@ApiModelProperty(value = "菜单列表")
	private List<SysMenu> menus;


	/**
	 * 权限列表
	 *
	 * @since 2023/04/09
	 */
	@ApiModelProperty(value = "权限列表")
	private List<String> perms;
}
