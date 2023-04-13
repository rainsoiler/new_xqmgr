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
 * 设备表
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("device")
@ApiModel(value = "Device对象", description = "设备表")
public class Device implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */

	@ApiModelProperty("id")
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 逻辑删除(0:正常,1:删除)
	 */

	@ApiModelProperty("逻辑删除(0:正常,1:删除)")
	@TableField("del_flag")
	@TableLogic
	private String delFlag;
	/**
	 * 修改人
	 */

	@ApiModelProperty("修改人")
	@TableField("update_by")
	private String updateBy;
	/**
	 * 创建人
	 */

	@ApiModelProperty("创建人")
	@TableField("create_by")
	private String createBy;
	/**
	 * 创建时间
	 */

	@ApiModelProperty("创建时间")
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */

	@ApiModelProperty("修改时间")
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;
	/**
	 * 品牌
	 */

	@ApiModelProperty("品牌")
	@TableField(value = "brand", condition = SqlCondition.LIKE)
	private String brand;
	/**
	 * 设备号
	 */

	@ApiModelProperty("设备号")
	@TableField(value = "sn", condition = SqlCondition.LIKE)
	private String sn;
	/**
	 * 状态
	 */

	@ApiModelProperty("状态")
	@TableField("status")
	private String status;
	/**
	 * app版本
	 */

	@ApiModelProperty("app版本")
	@TableField("app_version")
	private String appVersion;

	/**
	 * 店铺名称
	 *
	 * @since 2023/04/13
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "店铺名称")
	private String storeName;

	/**
	 * 区域名称
	 *
	 * @since 2023/04/13
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "区域名称")
	private String regionName;
}
