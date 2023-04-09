package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
 import org.springframework.web.bind.annotation.RestController;
<#else>
 import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
 import ${superControllerClassPackage};
</#if>

/**
*
* ${table.comment!} 前端控制器
*
*
* @author ${author}
* @since ${date}
*/
@Api(tags = "${table.comment!}")
@Slf4j
@RequiredArgsConstructor
<#if restControllerStyle>
 @RestController
<#else>
 @Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
 public class ${table.controllerName} extends ${superControllerClass} {
<#else>
 public class ${table.controllerName} {
</#if>

  private final ${table.serviceName} ${table.serviceName?uncap_first};

/**
* 分页
*
* @param pageRequest 分页参数
* @return com.github.rainsoil.fastapi.common.core.R<com.github.rainsoil.fastapi.common.core.PageInfo < com.github.rainsoil.fastapi.web.system.entity.${entity}>>
* @since 2023/04/08
*/
@ApiOperation(value = "分页")
@PostMapping("page")
public R<PageInfo<${entity}>> page(@RequestBody PageRequest<${entity}> pageRequest) {
    PageInfo<${entity}> pageInfo = ${table.serviceName?uncap_first}.page(pageRequest, new PageHandler<${entity}>() {


        });
        return R.ok(pageInfo);
        }

        /**
        * 保存
        *
        * @param ${entity?uncap_first} 实体类
        * @return com.github.rainsoil.fastapi.common.core.R
        * @since 2023-04-08
        */
        @PostMapping("")
        @ApiOperation(value = "保存")
        public R save(@RequestBody ${entity} ${entity?uncap_first}) {

        this.${table.serviceName?uncap_first}.save(${entity?uncap_first});
        return R.ok();
        }

        /**
        * 修改
        *
        * @param ${entity?uncap_first} 实体类
        * @return com.github.rainsoil.fastapi.common.core.R
        * @since 2023-04-08
        */
        @ApiOperation(value = "修改")
        @PutMapping("")
        public R update(@RequestBody ${entity} ${entity?uncap_first}) {
        this.${table.serviceName?uncap_first}.updateById(${entity?uncap_first});
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
        this.${table.serviceName?uncap_first}.removeById(id);
        return R.ok();
        }

      }
      </#if>
