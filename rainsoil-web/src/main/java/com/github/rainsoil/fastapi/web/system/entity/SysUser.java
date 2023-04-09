package com.github.rainsoil.fastapi.web.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
/**
* <p>
    * 系统用户表
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "系统用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * id
    */

    @ApiModelProperty("id")
	@TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
    * 创建时间
    */

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
    * 创建人
    */

    @ApiModelProperty("创建人")
    @TableField("create_by")
    private String createBy;
    /**
    * 修改时间
    */

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
    * 逻辑删除(0:正常,1:删除)
    */

    @ApiModelProperty("逻辑删除(0:正常,1:删除)")
    @TableField("del_flag")
    @TableLogic
    private String delFlag;
    /**
    * 头像
    */

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;
    /**
    * 手机号
    */

    @ApiModelProperty("手机号")
    @TableField(value = "mobile",condition = SqlCondition.LIKE)
    private String mobile;
    /**
    * 昵称
    */

    @ApiModelProperty("昵称")
    @TableField(value = "nickname",condition = SqlCondition.LIKE)
    private String nickname;
    /**
    * 密码
    */

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;
    /**
    * 角色id
    */

    @ApiModelProperty("角色id")
    @TableField("role_id")
    private String roleId;
    /**
    * 修改人
    */

    @ApiModelProperty("修改人")
    @TableField("update_by")
    private String updateBy;
    /**
    * 状态
    */

    @ApiModelProperty("状态")
    @TableField("status")
    private String status;
    /**
    * 用户名
    */

    @ApiModelProperty("用户名")
    @TableField(value = "username",condition = SqlCondition.LIKE)
    private String username;
}
