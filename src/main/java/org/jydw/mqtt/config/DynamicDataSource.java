package org.jydw.mqtt.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 抽象数据源
 * @author zhouliang
 * @date 2017年9月20日
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


	@Override
	protected Object determineCurrentLookupKey() {
		//默认是 source1
		DatabaseType currentDataSource = DatabaseContextHolder.getDatabaseType();
		if (currentDataSource == null){
			return DatabaseType.ds1;
		}
		return currentDataSource;
	}

}
