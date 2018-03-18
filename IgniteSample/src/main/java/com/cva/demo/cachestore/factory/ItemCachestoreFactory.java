
package com.cva.demo.cachestore.factory;

import javax.cache.configuration.Factory;

import org.apache.ignite.cache.store.CacheStore;

import com.cva.demo.cachestore.ItemSachemaCachestore;

public class ItemCachestoreFactory implements Factory<CacheStore<String, String>> {

	private  String connectionString=null;
	public ItemCachestoreFactory(String connectionString)
	{
		this.connectionString = connectionString;
	}
	
	@Override
	public CacheStore<String, String> create() {
		
		return new ItemSachemaCachestore(this.connectionString);
	}

	
}
