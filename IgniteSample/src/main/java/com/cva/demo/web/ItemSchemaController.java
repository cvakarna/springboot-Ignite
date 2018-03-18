package com.cva.demo.web;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cva.demo.config.CacheConfigurationSetting;
/**
 * This controller is serving crud operations for Item schema Json (used the Ingite 3rd party persistence)
 * @author 
 *
 */
@RestController
@RequestMapping("/service/item")
public class ItemSchemaController {

	@Autowired
	CacheConfigurationSetting cacheConfigSetting;

	@RequestMapping(value = "/schema", method = RequestMethod.GET)
	
	public ResponseEntity<String> getItemSchemaJson(@RequestParam String cacheName, @RequestParam String key) {

		IgniteCache<String, String> cache;
		try {
			cache = cacheConfigSetting.createOrGetCacheForSchema(cacheName,
					CacheAtomicityMode.ATOMIC);
			String value = cache.get(key);
			return new ResponseEntity<>(value, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
}
