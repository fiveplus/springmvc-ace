package com.fiveplus.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自定义Redis缓存（update对象必须与select对象一致）
 * @author five
 *
 */

public class RedisCache implements Cache{
	private static final Log log = LogFactory.getLog(RedisCache.class);
	private static JedisConnectionFactory jedisConnectionFactory;
	
	private final String id;
	
	/**
	 * The {@code ReadWriteLock}
	 */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	public RedisCache(final String id){
		if(id == null){
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		log.debug("MybatisRedisCache:id="+id);
		this.id = id;
	}
	
	@Override
	public void clear() {
		JedisConnection connection = null;
		try{
			connection = jedisConnectionFactory.getConnection();
			connection.flushDb();
			connection.flushAll();
			log.debug("redis clear.");
		}catch( JedisConnectionException e){
			e.printStackTrace();
		}finally{
			if(connection != null) {
				connection.close();
			}
		}
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public Object getObject(Object key) {
		Object result = null;
		JedisConnection connection = null;
		try{
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = serializer.deserialize(connection.get(serializer.serialize(key)));
			log.debug("getObject:"+key.toString());
		}catch(JedisConnectionException e){
			e.printStackTrace();
		}finally{
			if(connection != null){
				connection.close();
			}
		}
		return result;
	}
	
	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}
	
	@Override
	public int getSize() {
		int result = 0;
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = Integer.valueOf(connection.dbSize().toString());
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		}finally{
			if(connection != null){
				connection.close();
			}
		}
		return result;
	}
	
	@Override
	public void putObject(Object key, Object value) {
		JedisConnection connection = null;
		try{
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			connection.set(serializer.serialize(key), serializer.serialize(value));
			log.debug("putObject:"+serializer.serialize(key));
		}catch(JedisConnectionException e){
			e.printStackTrace();
		}finally{
			if(connection != null){
				connection.close();
			}
		}
	}
	
	@Override
	public Object removeObject(Object key) {
		JedisConnection connection = null;
		Object result = null;
		try {

			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = connection.expire(serializer.serialize(key), 0);
			log.debug("removeObject:"+serializer.serialize(key));

		} catch (JedisConnectionException e) {
			e.printStackTrace();
		}finally {
			if(connection != null){
				connection.close();
			}
		}
		return result;
	}
	
	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
		RedisCache.jedisConnectionFactory = jedisConnectionFactory;
	}
	
}
