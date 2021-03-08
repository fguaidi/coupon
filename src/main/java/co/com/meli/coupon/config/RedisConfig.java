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
    	  redisConf.setHostName("ec2-3-237-21-106.compute-1.amazonaws.com");
    	  redisConf.setPort(6379);
    	  redisConf.setPassword("Prrmm4TDSSca");
    	  return new LettuceConnectionFactory(redisConf);
    }
}