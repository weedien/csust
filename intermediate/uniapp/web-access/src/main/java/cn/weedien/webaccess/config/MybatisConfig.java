package cn.weedien.webaccess.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author weedien
 * @date 2023/12/15
 */
@Configuration
@MapperScan("cn.weedien.webaccess.mapper")
public class MybatisConfig {
}
