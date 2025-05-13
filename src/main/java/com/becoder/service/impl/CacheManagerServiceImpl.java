package com.becoder.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.becoder.service.CacheManagerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CacheManagerServiceImpl implements CacheManagerService {

	@Autowired
	private CacheManager cacheManager;

	@Override
	public Collection<String> getCache() {
		Collection<String> cacheNames = cacheManager.getCacheNames();
		for (String cacheName : cacheNames) {
			Cache cache = cacheManager.getCache(cacheName);
			log.info("Cache Name={}", cache);
		}
		return cacheNames;
	}

	@Override
	public Cache getCacheName(String cacheName) {

		Cache cache = cacheManager.getCache(cacheName);
		log.info("Cache Name= {}", cache);
		return cache;
	}

	@Override
	public void removeAllCache() {
		Collection<String> cacheNames = cacheManager.getCacheNames();
		for (String cacheName : cacheNames) {
			Cache cache = cacheManager.getCache(cacheName);
			log.info("Cache Name={}", cache);
			cache.clear();
		}
	}

	@Override
	public void removeCacheByName(List<String> cacheNames) {
		for (String cacheName : cacheNames) {
			Cache cache = cacheManager.getCache(cacheName);
			log.info("Cache Name={}", cache);
			cache.clear();
		}
	}
	
	

}
