package edu.mum.cs544;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String QUEUE = "post-queue"; //"bog_queue";
    public static String EXCHANGE = "blog-exchange";  //"blog_exchange";
    public static String ROUTING_KEY = "post-routing"; // "blog_routingKey";

    @Bean
    public Queue getQueue() {
        return new Queue(QUEUE);
    }
    @Bean
    public TopicExchange getExchange() {
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    public Binding getBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
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
