package com.cva.demo.cachestore;

import java.util.Collection;
import java.util.Map;

import javax.cache.Cache.Entry;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;

import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;

public class ItemSachemaCachestore  implements CacheStore<String, String> {
    String connectionString =null;
    public ItemSachemaCachestore(String connectionString) 
    {
    	this.connectionString = connectionString;
    }
	@Override
	public String load(String arg0) throws CacheLoaderException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, String> loadAll(Iterable<? extends String> arg0) throws CacheLoaderException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Object arg0) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll(Collection<?> arg0) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void write(Entry<? extends String, ? extends String> arg0) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void writeAll(Collection<Entry<? extends String, ? extends String>> arg0) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void loadCache(IgniteBiInClosure<String, String> clo, Object... args) throws CacheLoaderException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sessionEnd(boolean commit) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}
	
}
