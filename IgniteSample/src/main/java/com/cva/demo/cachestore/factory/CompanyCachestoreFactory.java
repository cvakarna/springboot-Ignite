package com.cva.demo.cachestore.factory;

import javax.cache.configuration.Factory;

import org.apache.ignite.cache.store.CacheStore;

import com.cva.demo.cachestore.CompanyCachestore;

public class CompanyCachestoreFactory implements Factory<CacheStore<String, String>> {
	private  String connectionString=null;
	public CompanyCachestoreFactory(String connectionString) {
		this.connectionString = connectionString;
	}
	@Override
	public CacheStore<String, String> create() {
		// TODO Auto-generated method stub
		return new CompanyCachestore(connectionString);
	}
    
	
}


