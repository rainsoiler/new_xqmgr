package com.github.rainsoil.fastapi.web.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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

    private static final long serialVersionUID = 1L;
    /**
    * 租户号
    */

    @ApiModelProperty("租户号")
    @TableField("TENANT_ID")
    private String tenantId;
    /**
    * 乐观锁
    */

    @ApiModelProperty("乐观锁")
    @TableField("REVISION")
    private Integer revision;
    /**
    * 创建人
    */

    @ApiModelProperty("创建人")
    @TableField("CREATED_BY")
    private String createdBy;
    /**
    * 创建时间
    */

    @ApiModelProperty("创建时间")
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;
    /**
    * 更新人
    */

    @ApiModelProperty("更新人")
    @TableField("UPDATED_BY")
    private String updatedBy;
    /**
    * 更新时间
    */

    @ApiModelProperty("更新时间")
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;
}
