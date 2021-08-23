package com.spring.messaging.PubSubDemo.config;

import com.spring.messaging.PubSubDemo.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    @Autowired
    private SubscriberService subscriberService;

    @Bean
    public StringRedisTemplate stringRedisTemplate(final RedisConnectionFactory connectionFactory) {
        final var redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    public MessageListenerAdapter messageListener() {

        return new MessageListenerAdapter(subscriberService);
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("demo-topic");
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(final RedisConnectionFactory connectionFactory) {
        final var container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener(), topic());
        return container;
    }
}
