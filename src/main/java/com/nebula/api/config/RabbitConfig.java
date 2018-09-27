package com.nebula.api.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class RabbitConfig {

	public static final String REVIEW_CREATED_QUEUE = "review-created-queue";

	public static final String QUEUE_DEAD_REVIEWS = "dead-review-created-queue";

	public static final String EXCHANGE_REVIEW_CREATED = "review-created-exchange";

	@Bean
	Queue ordersQueue() {

		return QueueBuilder
				.durable(REVIEW_CREATED_QUEUE)
				.withArgument("x-dead-letter-exchange", "")
				.withArgument("x-dead-letter-routing-key", QUEUE_DEAD_REVIEWS)
				.withArgument("x-message-ttl", 60000)
				.build();
	}

	@Bean
	Queue deadLetterQueue() {
		return QueueBuilder.durable(QUEUE_DEAD_REVIEWS).build();
	}

	@Bean
	Exchange ordersExchange() {
		return ExchangeBuilder.topicExchange(EXCHANGE_REVIEW_CREATED).build();
	}

	@Bean
	Binding binding(Queue ordersQueue, TopicExchange ordersExchange) {
		return BindingBuilder.bind(ordersQueue).to(ordersExchange).with(REVIEW_CREATED_QUEUE);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

}