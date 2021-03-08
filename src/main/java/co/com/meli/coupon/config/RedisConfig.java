package co.com.meli.coupon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(){
    	
    	 RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
    	  redisConf.setHostName("localhost");
    	  redisConf.setPort(6379);
    	  redisConf.setPassword("");
    	  return new LettuceConnectionFactory(redisConf);
    }
}