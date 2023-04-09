package com.github.rainsoil.fastapi.web.system.controller;

import com.github.rainsoil.fastapi.common.core.PageInfo;
import com.github.rainsoil.fastapi.common.core.PageRequest;
import com.github.rainsoil.fastapi.common.core.R;
import com.github.rainsoil.fastapi.common.core.mybatis.PageHandler;
import com.github.rainsoil.fastapi.web.system.entity.Device;
import com.github.rainsoil.fastapi.web.system.service.IDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
*
* 设备表 前端控制器
*
*
* @author luyanan
* @since 2023-04-09
*/
@Api(tags = "设备表")
@Slf4j
@RequiredArgsConstructor
 @RestController
@RequestMapping("/system/device")
 public class DeviceController {

  private final IDeviceService iDeviceService;

/**
* 分页
*
* @param pageRequest 分页参数
* @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.entity.Device>>
* @since 2023/04/08
*/
@ApiOperation(value = "分页")
@PostMapping("page")
public R<PageInfo<Device>> page(@RequestBody PageRequest<Device> pageRequest) {
    PageInfo<Device> pageInfo = iDeviceService.page(pageRequest, new PageHandler<Device>() {


        });
        return R.ok(pageInfo);
        }

        /**
        * 保存
        *
        * @param device 实体类
        * @return com.github.rainsoil.fastapi.common.core.R
        * @since 2023-04-08
        */
        @PostMapping("")
        @ApiOperation(value = "保存")
        public R save(@RequestBody Device device) {

        this.iDeviceService.save(device);
        return R.ok();
        }

        /**
        * 修改
        *
        * @param device 实体类
        * @return com.github.rainsoil.fastapi.common.core.R
        * @since 2023-04-08
        */
        @ApiOperation(value = "修改")
        @PutMapping("")
        public R update(@RequestBody Device device) {
        this.iDeviceService.updateById(device);
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
        this.iDeviceService.removeById(id);
        return R.ok();
        }

      }
