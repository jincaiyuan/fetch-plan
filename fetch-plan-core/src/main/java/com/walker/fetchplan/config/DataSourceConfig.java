package com.walker.fetchplan.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Getter
@Setter
@Configuration
@MapperScan(value = "com.walker.fetchplan.dal")
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class DataSourceConfig {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setJdbcUrl(jdbcUrl);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;

    }

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPlugins(pageInterceptor());
        Resource[] resources =  new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/*.xml");
        factoryBean.setMapperLocations(resources);
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
