package com.nebula.api.service;

import com.nebula.api.config.RabbitConfig;
import com.nebula.api.model.Review;
import com.nebula.api.model.ReviewStatus;
import com.nebula.api.repository.ReviewRepository;
import com.nebula.api.request_response.CreateReviewDto;
import com.nebula.api.request_response.CreateReviewRequest;
import com.nebula.api.request_response.CreateReviewResponse;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

	private final RabbitTemplate rabbitTemplate;

	private final ReviewRepository reviewRepository;

	public CreateReviewResponse create(CreateReviewRequest request) {

		final Review review = mapToReviewEntity(request);
		reviewRepository.save(review);

		rabbitTemplate.convertAndSend(RabbitConfig.REVIEW_CREATED_QUEUE, mapToCreateReviewDto(review));

		return new CreateReviewResponse(review.getId());
	}

	public List<Review> getAll() {
		return reviewRepository.findAll();
	}

	private Review mapToReviewEntity(CreateReviewRequest request) {
		final Review review = new Review();
		review.setProductId(request.getProductId());
		review.setReviewerName(request.getReviewerName());
		review.setEmailAddress(request.getEmailAddress());
		review.setRating(request.getRating());
		review.setComment(request.getComments());
		review.setStatus(ReviewStatus.PENDING);

		return review;
	}

	private CreateReviewDto mapToCreateReviewDto(Review review) {
		return new CreateReviewDto(review.getId(), review.getComment());
	}
}
