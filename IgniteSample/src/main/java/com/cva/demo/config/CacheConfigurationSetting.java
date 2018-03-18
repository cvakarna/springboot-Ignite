package com.cva.demo.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cva.demo.cachestore.factory.CompanyCachestoreFactory;
import com.cva.demo.cachestore.factory.ItemCachestoreFactory;
import com.cva.demo.model.Item;

@Component
public class CacheConfigurationSetting {

	@Autowired
	ConnectionProperty connProp;
	@Autowired
	Ignite ignite;
	
	/**
	 * This method is used for ItemSchema cache creation
	 * @param name
	 * @param atomic
	 * @return
	 * @throws Exception
	 */
	public IgniteCache<String, String> createOrGetCacheForSchema(String name, CacheAtomicityMode atomic)
			throws Exception {

		CacheConfiguration cacheCfg = new CacheConfiguration(name);

		cacheCfg.setReadThrough(true);
		cacheCfg.setWriteThrough(true);
		cacheCfg.setAtomicityMode(atomic);

		// get sql connection for item schema table
		String connectionString = getSqlConnection(name,atomic);
		// Configure store
		cacheCfg.setCacheStoreFactory(new ItemCachestoreFactory(connectionString));

		cacheCfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);

		return this.ignite.getOrCreateCache(cacheCfg);
	}
	/**
	 * This method is used for getting  connection string for ItemSchema table from the Company master table
	 * @param companyId
	 * @param atomic
	 * @return
	 * @throws Exception
	 */
	private String getSqlConnection(String companyId, CacheAtomicityMode atomic) throws Exception {

		// getting the connection string from the propertiesfile
		String sqlConnection = connProp.getmasterDbSqlserverUrl();
		CacheConfiguration cacheCfg = new CacheConfiguration("CompanyDetails");

		cacheCfg.setReadThrough(true);
		cacheCfg.setWriteThrough(true);
		cacheCfg.setAtomicityMode(atomic);

		// Configure store
		//to get the connection string for ItemSchema table from the Company master table
		cacheCfg.setCacheStoreFactory(new CompanyCachestoreFactory(sqlConnection.trim()));
		cacheCfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);
		IgniteCache<String, String> cache = ignite.getOrCreateCache(cacheCfg);

		String sqlConnectionString = cache.get("SQL_" + companyId);

		return sqlConnectionString;

	}
	/**
	 * This method is used for creating a cache configuration for the Company(using 3rd party persistence)
	 * @param name
	 * @param atomic
	 * @return
	 * @throws Exception
	 */
	public IgniteCache<String, String> createOrGetCacheForCompany(String name, CacheAtomicityMode atomic) throws Exception {


		CacheConfiguration cacheCfg = new CacheConfiguration(name);

		cacheCfg.setReadThrough(true);
		cacheCfg.setWriteThrough(true);
		cacheCfg.setAtomicityMode(atomic);

		// get sql connection
		String connectionString = getSqlConnection();

		// Configure store
		cacheCfg.setCacheStoreFactory(new CompanyCachestoreFactory(connectionString));

		cacheCfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);

		return this.ignite.getOrCreateCache(cacheCfg);

	}
	/**
	 * This Method is used for creating a cache for the Item 
	 * @param cacheName 
	 * @param atomic
	 * @return
	 * @throws Exception
	 */
	public IgniteCache<String, Item> createOrGetCacheForItem(String cacheName, CacheAtomicityMode atomic) throws Exception {

		CacheConfiguration<String, Item> cacheCfg = new CacheConfiguration<>(cacheName);
		cacheCfg.setCacheMode(CacheMode.PARTITIONED); // Default.
		cacheCfg.setIndexedTypes(String.class, Item.class);
		cacheCfg.setAtomicityMode(atomic);

		cacheCfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);

		return this.ignite.getOrCreateCache(cacheCfg);
	}
	private String getSqlConnection() {
		String connectionString = connProp.getmasterDbSqlserverUrl();
		return connectionString;

	}
}
