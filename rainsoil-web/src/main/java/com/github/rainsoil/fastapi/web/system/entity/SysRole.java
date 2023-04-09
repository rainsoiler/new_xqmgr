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
    * 角色表
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value = "SysRole对象", description = "角色表")
public class SysRole implements Serializable {

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
    * 角色编码
    */

    @ApiModelProperty("角色编码")
    @TableField("label")
    private String label;
    /**
    * 菜单id
    */

    @ApiModelProperty("菜单id")
    @TableField("menus")
    private String menus;
    /**
    * 角色名称
    */

    @ApiModelProperty("角色名称")
    @TableField("name")
    private String name;
    /**
    * 备注
    */

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
    /**
    * 修改人
    */

    @ApiModelProperty("修改人")
    @TableField("update_by")
    private String updateBy;
}
