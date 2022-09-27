package edu.miu.cs455.users.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    //@Value("${queue}")
    public static final String QUEUE = "comment-queue"; //"bog_queue";
    //@Value("${exchange}")
    public static String EXCHANGE = "blog-exchange";  //"blog_exchange";
    // @Value("${routing_key}")
    public static String ROUTING_KEY = "comment-routing"; // "blog_routingKey";

    @Bean
    public Queue getQueue(){
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding getBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter getConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getConverter());
        return rabbitTemplate;
    }
}
