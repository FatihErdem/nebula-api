package com.nebula.api.consumer;

import com.nebula.api.config.RabbitConfig;
import com.nebula.api.model.Review;
import com.nebula.api.model.dto.UpdateReviewDto;
import com.nebula.api.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ReviewUpdatedConsumer {

	private final ReviewService reviewService;

	@RabbitListener(queues = RabbitConfig.REVIEW_UPDATED_QUEUE)
	public void consumeReviewUpdate(UpdateReviewDto dto) {

		Review review = reviewService.getById(dto.getId());
		review.setId(dto.getId());
		review.setStatus(dto.getStatus());

		reviewService.updateStatus(review);
		reviewService.sendEmail(review);
	}
}
