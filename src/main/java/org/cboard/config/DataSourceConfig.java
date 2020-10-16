package org.cboard.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author WangKun
 * @create 2018-07-25
 * @desc
 **/
@Import(PropertiesConfig.class)
public class DataSourceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Bean(name = "h2DataSource")
    public BasicDataSource basicDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.h2.Driver");
        basicDataSource.setUrl(propertiesConfig.getH2Url());
        basicDataSource.setUsername(propertiesConfig.getH2UserName());
        basicDataSource.setPassword("");
        basicDataSource.setMaxTotal(20);
        LOGGER.info("h2DataSource init ok");
        return basicDataSource;
    }

    @Primary
    @Bean(name = "dataSource")
    public DruidDataSource druidDataSource(DataSourceProperties dataSourceProperties) {
        return DruidDataSourceBuilder.create().build();
    }

//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactoryBean sqlSessionFactoryBean(DruidDataSource druidDataSource) {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(druidDataSource);
//        try {
//            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
//            Properties properties = new Properties();
//            InputStream in = DataSourceConfig.class.getClassLoader().getResourceAsStream("application.yml");
//            properties.load(in);
//            sqlSessionFactoryBean.setConfigurationProperties(properties);
//        } catch (IOException e) {
//            LOGGER.error("sqlSessionFactoryBean setMapperLocations is error", e);
//        }
//        return sqlSessionFactoryBean;
//    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(DruidDataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean(name = "druid-stat-interceptor")
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    @Bean(name = "druid-stat-pointcut")
    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut() {
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
        jdkRegexpMethodPointcut.setPattern("org.cboard.dao.*");
        return jdkRegexpMethodPointcut;
    }

    @Bean
    public Advisor druidStatAdvisor(JdkRegexpMethodPointcut jdkRegexpMethodPointcut, DruidStatInterceptor druidStatInterceptor) {
        return new DefaultPointcutAdvisor(jdkRegexpMethodPointcut, druidStatInterceptor);
    }
}
