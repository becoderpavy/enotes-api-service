package com.becoder.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.endpoint.CacheEndpoint;
import com.becoder.service.CacheManagerService;
import com.becoder.util.CommonUtil;

@RestController
public class CacheController implements CacheEndpoint {

	@Autowired
	private CacheManagerService cacheService;

	@Override
	public ResponseEntity<?> getAllCache() {
		Collection<String> cache = cacheService.getCache();
		return CommonUtil.createBuildResponse(cache, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getCache(String cache_name) {
		Cache cacheName = cacheService.getCacheName(cache_name);
		return CommonUtil.createBuildResponse(cacheName, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> removeAllCache() {
		cacheService.removeAllCache();
		return CommonUtil.createBuildResponseMessage("Remove all cache", HttpStatus.OK);
	}

}
