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
 * 微信用户
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("wx_user")
@ApiModel(value = "WxUser对象", description = "微信用户")
public class WxUser implements Serializable {

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
//	@TableLogic
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
	 * 状态
	 */

	@ApiModelProperty("状态")
	@TableField("status")
	private String status;
	/**
	 * 是否为admin
	 */

	@ApiModelProperty("是否为admin")
	@TableField("admin")
	private String admin;
	/**
	 * 昵称
	 */

	@ApiModelProperty("昵称")
	@TableField("nickname")
	private String nickname;
	/**
	 * 店铺id
	 */

	@ApiModelProperty("店铺id")
	@TableField("store_id")
	private String storeId;
}
