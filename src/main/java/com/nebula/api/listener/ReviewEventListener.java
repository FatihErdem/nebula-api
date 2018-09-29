package com.nebula.api.listener;

import com.nebula.api.config.RabbitConfig;
import com.nebula.api.model.dto.EmailReviewDto;
import com.nebula.api.service.ReviewMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ReviewEventListener {

	private final RabbitTemplate rabbitTemplate;

	@EventListener
	public void handleReviewCreateEvent(ReviewCreateEvent event) {

		if (event != null && event.getCreateReviewDto() != null) {
			rabbitTemplate.convertAndSend(RabbitConfig.REVIEW_CREATED_QUEUE, event.getCreateReviewDto());
		}
	}

	@EventListener
	public void handleSendEmailEvent(SendEmailEvent event) {

		if (event != null && event.getReview() != null) {
			EmailReviewDto emailReviewDto = ReviewMapper.mapToEmailReviewDto(event.getReview());
			rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_QUEUE, emailReviewDto);
		}
	}

}
