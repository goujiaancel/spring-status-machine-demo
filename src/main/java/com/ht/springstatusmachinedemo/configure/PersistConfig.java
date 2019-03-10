package com.ht.springstatusmachinedemo.configure;

import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import com.ht.springstatusmachinedemo.persist.InMemoryStateMachinePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class PersistConfig {

    @Autowired
    private InMemoryStateMachinePersist inMemoryStateMachinePersist;

    @Bean(name = "inMemoryPersister")
    public StateMachinePersister<States, Events, String> getPersister() {
        return new DefaultStateMachinePersister<>(inMemoryStateMachinePersist);
    }

    @Bean(name = "redisPersister")
    @Primary
    public RedisStateMachinePersister<States, Events> redisPersister() {
        return new RedisStateMachinePersister<>(redisPersist());
    }

    public StateMachinePersist<States, Events, String> redisPersist() {
        RedisStateMachineContextRepository<States, Events> repository = new RedisStateMachineContextRepository<>(redisConnectionFactory());
        return new RepositoryStateMachinePersist<>(repository);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        jedisConnectionFactory.setDatabase(1);
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setUsePool(true);
        return jedisConnectionFactory;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(60);
        jedisPoolConfig.setMaxWaitMillis(-1);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setMaxTotal(60);
        return jedisPoolConfig;
    }


}
