package com.github.rainsoil.fastapi.web.security;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录用户
 *
 * @author luyanan
 * @since 2023/04/09
 */
@Data
public class LoginUser implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */

	@ApiModelProperty("id")
	private String id;
	/**
	 * 创建时间
	 */

	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;
	/**
	 * 创建人
	 */

	@ApiModelProperty("创建人")
	private String createBy;
	/**
	 * 修改时间
	 */

	@ApiModelProperty("修改时间")
	private LocalDateTime updateTime;

	/**
	 * 头像
	 */

	@ApiModelProperty("头像")
	private String avatar;
	/**
	 * 手机号
	 */

	@ApiModelProperty("手机号")
	private String mobile;
	/**
	 * 昵称
	 */

	@ApiModelProperty("昵称")
	private String nickname;
	/**
	 * 密码
	 */

	@ApiModelProperty("密码")
	private String password;
	/**
	 * 角色id
	 */

	@ApiModelProperty("角色id")
	private String roleId;
	/**
	 * 修改人
	 */

	@ApiModelProperty("修改人")
	private String updateBy;
	/**
	 * 状态
	 */

	@ApiModelProperty("状态")
	private String status;
	/**
	 * 用户名
	 */

	@ApiModelProperty("用户名")
	private String username;

	/**
	 * 授权列表
	 *
	 * @param null
	 * @return
	 * @since 2022/10/14
	 */
	private List<String> auths;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (CollectionUtils.isEmpty(this.auths)) {
			return null;
		}
		return AuthorityUtils.createAuthorityList(this.auths.stream().collect(Collectors.joining(",")));
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
