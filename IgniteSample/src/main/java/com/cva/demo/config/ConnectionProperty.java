package com.cva.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ConnectionProperty {
	
	@Value("${masterDbSqlserverUrl}")
	private String masterDbSqlserverUrl;

	public String getmasterDbSqlserverUrl() {
		return masterDbSqlserverUrl;
	}	
}
