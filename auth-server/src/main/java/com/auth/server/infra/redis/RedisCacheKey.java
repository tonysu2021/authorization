package com.auth.server.infra.redis;

public enum RedisCacheKey {
	USER(CacheKey.USER, 60 * 60);

	RedisCacheKey() {
	}

	RedisCacheKey(String name, int keepSecond) {
		this.name = name;
		this.keepSecond = keepSecond;
	}

	private String name;
	private int keepSecond;

	public String getName() {
		return name;
	}

	public int getKeepSecond() {
		return keepSecond;
	}
}
