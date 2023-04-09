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
    * app信息表
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("app_info")
@ApiModel(value = "AppInfo对象", description = "app信息表")
public class AppInfo implements Serializable {

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
    * 版本
    */

    @ApiModelProperty("版本")
    @TableField(value = "ver_name",condition = SqlCondition.LIKE)
    private String verName;
    /**
    * 文件url
    */

    @ApiModelProperty("文件url")
    @TableField("file_url")
    private String fileUrl;
    /**
    * 版本介绍
    */

    @ApiModelProperty("版本介绍")
    @TableField(value = "description",condition = SqlCondition.LIKE)
    private String description;
    /**
    * 是否强制更新
    */

    @ApiModelProperty("是否强制更新")
    @TableField("force_upgrade")
    private String forceUpgrade;
    /**
    * 状态
    */

    @ApiModelProperty("状态")
    @TableField("status")
    private String status;
}
