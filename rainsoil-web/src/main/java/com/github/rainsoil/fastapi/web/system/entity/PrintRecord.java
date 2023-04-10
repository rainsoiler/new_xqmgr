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
 * 打印记录
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("print_record")
@ApiModel(value = "PrintRecord对象", description = "打印记录")
public class PrintRecord implements Serializable {

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
	 * 打印机编码
	 */
	@ApiModelProperty(name = "打印机编码", notes = "")
	private String code;
	/**
	 * 店铺id
	 */
	@ApiModelProperty(name = "店铺id", notes = "")
	private String storeId;
	/**
	 * 区域id
	 */
	@ApiModelProperty(name = "区域id", notes = "")
	private String regionId;
	/**
	 * 公司id
	 */
	@ApiModelProperty(name = "公司id", notes = "")
	private String companyId;
	/**
	 * 物料id
	 */
	@ApiModelProperty(name = "物料id", notes = "")
	private String materialId;
	/**
	 * 物料名称
	 */
	@ApiModelProperty(name = "物料名称", notes = "")
	private String materialName;
	/**
	 * 店铺名称
	 */
	@ApiModelProperty(name = "店铺名称", notes = "")
	private String storeName;
	/**
	 * 区域名称
	 */
	@ApiModelProperty(name = "区域名称", notes = "")
	private String regionName;
	/**
	 * 打印时间
	 */
	@ApiModelProperty(name = "打印时间", notes = "")
	private LocalDateTime printTime;
	/**
	 * 过期时间
	 */
	@ApiModelProperty(name = "过期时间", notes = "")
	private LocalDateTime expirationTime;
	/**
	 * 过期毫秒数
	 */
	@ApiModelProperty(name = "过期毫秒数", notes = "")
	private Long expirationMs;
	/**
	 * 保存时间
	 */
	@ApiModelProperty(name = "保存时间", notes = "")
	private String saveTime;
	/**
	 * 状态
	 */
	@ApiModelProperty(name = "状态", notes = "")
	private String status;
	/**
	 * 打印毫秒数
	 */
	@ApiModelProperty(name = "打印毫秒数", notes = "")
	private Long printMs;

}
