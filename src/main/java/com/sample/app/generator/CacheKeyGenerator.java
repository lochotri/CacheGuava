package com.sample.app.generator;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

@Component("myCacheKeyGenerator")
public class CacheKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		StringBuilder sb = new StringBuilder();



		if (params != null) {
			for (Object param : params) {
				sb.append(param);
			}
		}
		return sb.toString();
	}

}
