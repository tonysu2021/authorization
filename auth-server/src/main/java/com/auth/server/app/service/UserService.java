package com.auth.server.app.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.auth.server.domain.entity.TbUser;
import com.auth.server.domain.service.TbUserService;
import com.auth.server.infra.redis.CacheKey;
import com.auth.server.web.dto.request.UserPatchRequest;

@Service("userService")
@CacheConfig(cacheNames = CacheKey.USER)
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	@Qualifier("tbUserService")
	private TbUserService tbUserService;
	
	@Async("CustomAsyncExecutor")
	public CompletableFuture<List<TbUser>> findAll() {
		var result = tbUserService.findAll();
		return CompletableFuture.completedFuture(result);
	}

	@Cacheable(key = "{#userName, 'username'}", unless = "#result == null")
	public Optional<TbUser> findByUserName(String userName) {
		return tbUserService.findByUserName(userName);
	}

	@Cacheable(key = "{#userId, 'userid'}", unless = "#result == null")
	public Optional<TbUser> findById(String userId) {
		return tbUserService.findById(userId);
	}

	@Caching(evict = {
			@CacheEvict(key = "{#tbUser.userName, 'username'}"),
		    @CacheEvict(key = "{'all'}")
	})
	public TbUser updateUser(TbUser tbUser, UserPatchRequest request) {
		return tbUserService.updateUser(tbUser, request);
	}

	@CacheEvict(value = CacheKey.USER, allEntries = true)
	public void clearCache() {
		logger.info("Clear all user cache.");
	}
}
