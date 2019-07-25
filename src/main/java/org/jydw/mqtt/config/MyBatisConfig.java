package org.jydw.mqtt.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


//import com.github.pagehelper.PageHelper;
//import com.xbsafe.common.interceptors.PaginationInterceptor.PaginationInterceptor;

/**
 * 多数据源配置
 * @author zhouliang
 * @date 2017年9月20日
 */
@Configuration
public class MyBatisConfig {
	@Autowired
	private Environment env;


	/**
	 * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
	 */
	@Bean
	public DataSource dataSource1() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", env.getProperty("ds1.datasource.driver-class-name"));
		props.put("url", env.getProperty("ds1.datasource.url"));
		props.put("username", env.getProperty("ds1.datasource.username"));
		props.put("password", env.getProperty("ds1.datasource.password"));
		return DruidDataSourceFactory.createDataSource(props);
	}

	@Bean
	public DataSource dataSource2() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", env.getProperty("ds2.datasource.driver-class-name"));
		props.put("url", env.getProperty("ds2.datasource.url"));
		props.put("username", env.getProperty("ds2.datasource.username"));
		props.put("password", env.getProperty("ds2.datasource.password"));
		return DruidDataSourceFactory.createDataSource(props);
	}

	/**
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	 * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
	 */
	@Bean
	@Primary
	public DynamicDataSource dataSource(
			@Qualifier("dataSource1") DataSource v8Source,
			@Qualifier("dataSource2") DataSource ns2000Source) {
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put(DatabaseType.ds1, v8Source);
		targetDataSources.put(DatabaseType.ds2, ns2000Source);

		DynamicDataSource ds = new DynamicDataSource();
		// 该方法是AbstractRoutingDataSource的方法
		ds.setTargetDataSources(targetDataSources);
		return ds;
	}

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds)
			throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		// 指定数据源(这个必须有，否则报错)
		fb.setDataSource(ds);
		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		// 指定基包
		fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
		fb.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources(env.getProperty("mybatis.mapperLocations")));
		return fb.getObject();
	}

}
