package cn.mr8god.fruitweb.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Mr8god
 * @date 2020年11月26日03:53:39
 * @time 2020年11月26日03:53:41
 */
@Configuration
@EnableTransactionManagement
@MapperScan("cn.mr8god.fruitweb.dao")
@PropertySource("classpath:application.properties")
public class MybatisConfig {
    @Autowired
    Environment env;
    @Bean
    @Qualifier("dataSoure")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        druidDataSource.setUsername(env.getProperty("jdbc.user"));
        druidDataSource.setPassword(env.getProperty("jdbc.password"));
        druidDataSource.setUrl(env.getProperty("jdbc.url"));
        return druidDataSource;
    }

    @Bean
    @Qualifier("dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(druidDataSource());
        return dataSourceTransactionManager;
    }

    @Bean
    @Qualifier("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource());
        return sqlSessionFactoryBean;
    }
}
