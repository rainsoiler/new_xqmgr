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
    * 物料表
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("material")
@ApiModel(value = "Material对象", description = "物料表")
public class Material implements Serializable {

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
    * 修改人
    */

    @ApiModelProperty("修改人")
    @TableField("update_by")
    private String updateBy;
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
    * 公司id
    */

    @ApiModelProperty("公司id")
    @TableField("company_id")
    private String companyId;
    /**
    * 物料名称
    */

    @ApiModelProperty("物料名称")
    @TableField(value = "name",condition = SqlCondition.LIKE)
    private String name;
    /**
    * 区域id
    */

    @ApiModelProperty("区域id")
    @TableField("region_id")
    private String regionId;
    /**
    * 是否提醒
    */

    @ApiModelProperty("是否提醒")
    @TableField("remind")
    private String remind;
    /**
    * 保存时间
    */

    @ApiModelProperty("保存时间")
    @TableField("saveTime")
    private String saveTime;
    /**
    * 状态(1:开启, 0:关闭)
    */

    @ApiModelProperty("状态(1:开启, 0:关闭)")
    @TableField("status")
    private String status;
    /**
    * 存储方式
    */

    @ApiModelProperty("存储方式")
    @TableField("storage_id")
    private String storageId;
    /**
    * 解冻时间
    */

    @ApiModelProperty("解冻时间")
    @TableField("thawTime")
    private String thawTime;
    /**
    * 是否解冻(1:是,0：否)
    */

    @ApiModelProperty("是否解冻(1:是,0：否)")
    @TableField("thawValid")
    private String thawValid;
    /**
    * 当天有效(1:是,0:否)
    */

    @ApiModelProperty("当天有效(1:是,0:否)")
    @TableField("todayValid")
    private String todayValid;
    /**
    * 物料分类
    */

    @ApiModelProperty("物料分类")
    @TableField("type_id")
    private String typeId;
    /**
    * 计算方式(0:小时,1:0+)
    */

    @ApiModelProperty("计算方式(0:小时,1:0+)")
    @TableField("computeMode")
    private String computeMode;
    /**
    * 保存包含解冻（1:包含,0：不包含）
    */

    @ApiModelProperty("保存包含解冻（1:包含,0：不包含）")
    @TableField("containsThaw")
    private String containsThaw;
    /**
    * 
    */

    @TableField("printMs")
    private String printMs;
}
