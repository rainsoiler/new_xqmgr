package com.github.rainsoil.fastapi.web.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 微信用户vo类
 *
 * @author luyanan
 * @since 2023/04/10
 */
public class WxUserVo {


	/**
	 * 微信小程序-用户vo类
	 *
	 * @author luyanan
	 * @since 2023/04/10
	 */
	@Data
	public static class WxUserMiniVo {
		/**
		 * id
		 */

		@ApiModelProperty("id")
		@TableId(value = "id", type = IdType.ASSIGN_ID)
		private String id;

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
		private String storeId;

		/**
		 * 设备SN
		 */

		@ApiModelProperty("设备SN")
		private String deviceSN;


		/**
		 * 店铺名称
		 *
		 * @since 2023/04/10
		 */
		@ApiModelProperty(value = "店铺id")
		private String storeName;
	}


	/**
	 * 绑定店员vo类
	 *
	 * @author luyanan
	 * @since 2023/04/11
	 */
	@Data
	public static class BindClerkVo {

		/**
		 * 昵称
		 */

		@ApiModelProperty("昵称")
		private String nickname;


		/**
		 * id
		 */

		@ApiModelProperty("id")
		private String id;
	}


	public static class WxLoginRes {

		private String openId;
	}

}
