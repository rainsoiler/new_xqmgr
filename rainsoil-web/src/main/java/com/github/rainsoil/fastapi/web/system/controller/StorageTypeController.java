package com.github.rainsoil.fastapi.web.system.controller;

import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.mybatis.PageHandler;
import com.github.rainsoil.fastapi.web.system.entity.StorageType;
import com.github.rainsoil.fastapi.web.system.service.IStorageTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
*
* 存储方式 前端控制器
*
*
* @author luyanan
* @since 2023-04-09
*/
@Api(tags = "存储方式")
@Slf4j
@RequiredArgsConstructor
 @RestController
@RequestMapping("/system/storageType")
 public class StorageTypeController {

  private final IStorageTypeService iStorageTypeService;

/**
* 分页
*
* @param pageRequest 分页参数
* @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.entity.StorageType>>
* @since 2023/04/08
*/
@ApiOperation(value = "分页")
@PostMapping("page")
public R<PageInfo<StorageType>> page(@RequestBody PageRequest<StorageType> pageRequest) {
    PageInfo<StorageType> pageInfo = iStorageTypeService.page(pageRequest, new PageHandler<StorageType>() {


        });
        return R.ok(pageInfo);
        }

        /**
        * 保存
        *
        * @param storageType 实体类
        * @return com.github.rainsoil.fastapi.common.core.R
        * @since 2023-04-08
        */
        @PostMapping("")
        @ApiOperation(value = "保存")
        public R save(@RequestBody StorageType storageType) {

        this.iStorageTypeService.save(storageType);
        return R.ok();
        }

        /**
        * 修改
        *
        * @param storageType 实体类
        * @return com.github.rainsoil.fastapi.common.core.R
        * @since 2023-04-08
        */
        @ApiOperation(value = "修改")
        @PutMapping("")
        public R update(@RequestBody StorageType storageType) {
        this.iStorageTypeService.updateById(storageType);
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
        this.iStorageTypeService.removeById(id);
        return R.ok();
        }

      }
