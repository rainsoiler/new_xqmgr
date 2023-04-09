package com.github.rainsoil.fastapi.web.system.mapper;

import com.github.rainsoil.fastapi.web.system.entity.SysUser;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Mapper
public interface SysUserMapper extends BaseBaseMapper<SysUser> {

}
