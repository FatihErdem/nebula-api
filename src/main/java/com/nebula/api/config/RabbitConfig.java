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

	public static final String REVIEW_UPDATED_QUEUE = "review-updated-queue";

	private static final String QUEUE_DEAD_CRATED_REVIEWS = "dead-review-created-queue";

	private static final String EXCHANGE_REVIEW_CREATED = "review-created-exchange";

	private static final String QUEUE_DEAD_UPDATED_REVIEWS = "dead-review-updated-queue";

	private static final String EXCHANGE_REVIEW_UPDATED = "review-updated-exchange";

	@Bean("reviewCreatedQueue")
	Queue reviewCreatedQueue() {

		return QueueBuilder
				.durable(REVIEW_CREATED_QUEUE)
				.withArgument("x-dead-letter-exchange", "")
				.withArgument("x-dead-letter-routing-key", QUEUE_DEAD_CRATED_REVIEWS)
				.withArgument("x-message-ttl", 60000)
				.build();
	}

	@Bean("reviewUpdatedQueue")
	Queue reviewUpdatedQueue() {

		return QueueBuilder
				.durable(REVIEW_UPDATED_QUEUE)
				.withArgument("x-dead-letter-exchange", "")
				.withArgument("x-dead-letter-routing-key", QUEUE_DEAD_UPDATED_REVIEWS)
				.withArgument("x-message-ttl", 60000)
				.build();
	}

	@Bean("deadLetterQueueReviewCrated")
	Queue deadLetterQueueReviewCrated() {
		return QueueBuilder.durable(QUEUE_DEAD_CRATED_REVIEWS).build();
	}

	@Bean("reviewCreatedExchange")
	Exchange reviewCreatedExchange() {
		return ExchangeBuilder.topicExchange(EXCHANGE_REVIEW_CREATED).build();
	}

	@Bean("reviewCreatedBinding")
	Binding reviewCreatedBinding(Queue reviewCreatedQueue, TopicExchange reviewCreatedExchange) {
		return BindingBuilder.bind(reviewCreatedQueue).to(reviewCreatedExchange).with(EXCHANGE_REVIEW_CREATED);
	}

	@Bean("deadLetterQueueReviewUpdated")
	Queue deadLetterQueueReviewUpdated() {
		return QueueBuilder.durable(QUEUE_DEAD_UPDATED_REVIEWS).build();
	}

	@Bean("reviewUpdatedExchange")
	Exchange reviewUpdatedExchange() {
		return ExchangeBuilder.topicExchange(EXCHANGE_REVIEW_UPDATED).build();
	}

	@Bean("reviewUpdatedBinding")
	Binding reviewUpdatedBinding(Queue reviewUpdatedQueue, TopicExchange reviewUpdatedExchange) {
		return BindingBuilder.bind(reviewUpdatedQueue).to(reviewUpdatedExchange).with(EXCHANGE_REVIEW_UPDATED);
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