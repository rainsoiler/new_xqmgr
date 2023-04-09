package com.github.rainsoil.fastapi.web.system.controller;

import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.mybatis.PageHandler;
import com.github.rainsoil.fastapi.web.system.entity.MaterialType;
import com.github.rainsoil.fastapi.web.system.service.IMaterialTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
*
* 物料分类 前端控制器
*
*
* @author luyanan
* @since 2023-04-09
*/
@Api(tags = "物料分类")
@Slf4j
@RequiredArgsConstructor
 @RestController
@RequestMapping("/system/materialType")
 public class MaterialTypeController {

  private final IMaterialTypeService iMaterialTypeService;

/**
* 分页
*
* @param pageRequest 分页参数
* @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.entity.MaterialType>>
* @since 2023/04/08
*/
@ApiOperation(value = "分页")
@PostMapping("page")
public R<PageInfo<MaterialType>> page(@RequestBody PageRequest<MaterialType> pageRequest) {
    PageInfo<MaterialType> pageInfo = iMaterialTypeService.page(pageRequest, new PageHandler<MaterialType>() {


        });
        return R.ok(pageInfo);
        }

        /**
        * 保存
        *
        * @param materialType 实体类
        * @return com.github.rainsoil.fastapi.common.core.R
        * @since 2023-04-08
        */
        @PostMapping("")
        @ApiOperation(value = "保存")
        public R save(@RequestBody MaterialType materialType) {

        this.iMaterialTypeService.save(materialType);
        return R.ok();
        }

        /**
        * 修改
        *
        * @param materialType 实体类
        * @return com.github.rainsoil.fastapi.common.core.R
        * @since 2023-04-08
        */
        @ApiOperation(value = "修改")
        @PutMapping("")
        public R update(@RequestBody MaterialType materialType) {
        this.iMaterialTypeService.updateById(materialType);
        return R.ok();
        }

        /**
        * 根据id删除
        *
        * @param id id
        * @return com.github.rainsoil.fastapi.common.core.R
        * @since 2023-04-08
        */
        @DeleteMapping()
        @ApiOperation(value = "根据id删除")
        public R remove(@RequestParam(value = "id", required = true) Long id) {
        this.iMaterialTypeService.removeById(id);
        return R.ok();
        }

      }
