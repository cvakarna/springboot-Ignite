package com.cva.demo.services;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cva.demo.config.CacheConfigurationSetting;

@Service
public class CompanyService {

	@Autowired
	CacheConfigurationSetting cacheConfigSetting;
	
	 public String getcompanyName(String cacheName,String companyId) throws Exception
	 {
		
		IgniteCache<String,String> cache = cacheConfigSetting.createOrGetCacheForCompany(cacheName, CacheAtomicityMode.ATOMIC);
	    String companyName = cache.get(companyId);
	    return companyName;
     }
}
