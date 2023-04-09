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
    * 店铺表
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("store")
@ApiModel(value = "Store对象", description = "店铺表")
public class Store implements Serializable {

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
    * 地址
    */

    @ApiModelProperty("地址")
    @TableField(value = "address",condition = SqlCondition.LIKE)
    private String address;
    /**
    * 公司id
    */

    @ApiModelProperty("公司id")
    @TableField("company_id")
    private String companyId;
    /**
    * 设备SN
    */

    @ApiModelProperty("设备SN")
    @TableField(value = "equipment_sn",condition = SqlCondition.LIKE)
    private String equipmentSn;
    /**
    * 手机号
    */

    @ApiModelProperty("手机号")
    @TableField(value = "mobile",condition = SqlCondition.LIKE)
    private String mobile;
    /**
    * 名称
    */

    @ApiModelProperty("名称")
    @TableField(value = "name",condition = SqlCondition.LIKE)
    private String name;
    /**
    * 地区id
    */

    @ApiModelProperty("地区id")
    @TableField("region_id")
    private String regionId;
    /**
    * 状态
    */

    @ApiModelProperty("状态")
    @TableField("status")
    private String status;
}
