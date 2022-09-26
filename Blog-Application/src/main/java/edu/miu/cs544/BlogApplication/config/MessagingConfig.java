package edu.miu.cs544.BlogApplication.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    public static final String POST_QUEUE = "post-queue";
    public static final String COMMENT_QUEUE = "comment-queue";
    public static final String USER_QUEUE = "user-queue";

    public static final String POST_ROUTING_KEY = "post-routing";
    public static final String COMMENT_ROUTING_KEY = "comment-routing";
    public static final String USER_ROUTING_KEY = "user-routing";

    public static final String EXCHANGE = "blog-exchange";
    @Bean
    public Queue getPostQueue() {
        return new Queue(POST_QUEUE);
    }
    @Bean
    public Queue getCommentQueue() {
        return new Queue(COMMENT_QUEUE);
    }
    @Bean
    public Queue getUserQueue() {
        return new Queue(USER_QUEUE);
    }
    @Bean
    public TopicExchange getExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding getPostBinding(@Qualifier("getPostQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(POST_ROUTING_KEY);
    }
    @Bean
    public Binding getCommentBinding(@Qualifier("getCommentQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(COMMENT_ROUTING_KEY);
    }
    @Bean
    public Binding getUserBinding(@Qualifier("getUserQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(USER_ROUTING_KEY);
    }
    @Bean
    public MessageConverter getConvertor() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory, MessageConverter converter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }
}
