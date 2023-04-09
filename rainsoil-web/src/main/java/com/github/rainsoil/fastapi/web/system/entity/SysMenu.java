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
 * 菜单表
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "菜单表")
public class SysMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 图标
	 */

	@ApiModelProperty("图标")
	@TableField("icon")
	private String icon;
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
	 * 保持缓存
	 */

	@ApiModelProperty("保持缓存")
	@TableField("keep_alive")
	private String keepAlive;
	/**
	 * 菜单名称
	 */

	@ApiModelProperty("菜单名称")
	@TableField("name")
	private String name;
	/**
	 * 排序
	 */

	@ApiModelProperty("排序")
	@TableField("order_num")
	private Integer orderNum;
	/**
	 * 路由地址
	 */

	@ApiModelProperty("路由地址")
	@TableField("router")
	private String router;
	/**
	 * 是否展示
	 */

	@ApiModelProperty("是否展示")
	@TableField("`show`")
	private String show;
	/**
	 * 修改人
	 */

	@ApiModelProperty("修改人")
	@TableField("update_by")
	private String updateBy;
	/**
	 * 类型
	 */

	@ApiModelProperty("类型")
	@TableField("type")
	private String type;

	/**
	 * 权限
	 *
	 * @since 2023/04/09
	 */
	@ApiModelProperty(value = "全新啊")
	private String perms;
}
