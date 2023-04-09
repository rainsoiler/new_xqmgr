package com.github.rainsoil.fastapi.web.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全配置类
 *
 * @author luyanan
 * @since 2023/04/09
 */
@Data
@ConfigurationProperties(prefix = "spring.security")
public class SpringSecurityProperties {


}
