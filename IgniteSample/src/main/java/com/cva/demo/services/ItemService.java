package com.cva.demo.services;

import java.util.List;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cva.demo.config.CacheConfigurationSetting;
import com.cva.demo.model.Item;
import com.google.gson.Gson;

@Service
public class ItemService {

	@Autowired
	CacheConfigurationSetting cacheConfigSetting;
	 public void createItemService(String cacheName,String itemId,String itemJson) throws Exception
	 {
		 IgniteCache<String,Item> cache = cacheConfigSetting.createOrGetCacheForItem(cacheName, CacheAtomicityMode.ATOMIC);
		 Gson gson = new Gson();
		 Item item = gson.fromJson(itemJson, Item.class);
		 cache.put(itemId,item);
	 }
	 public String queryIgnite(String cacheName,String query) throws Exception
	 {
		 IgniteCache<?, ?> cache =  Ignition.ignite().cache(cacheName);

			SqlFieldsQuery sql = new SqlFieldsQuery(query);
			QueryCursor<List<?>> cursor = cache.query(sql);
			Gson gson = new Gson();
			String result = gson.toJson(cursor.getAll());

			return result;
	 }
}
