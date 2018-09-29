package com.nebula.api.service;

import com.nebula.api.config.RabbitConfig;
import com.nebula.api.model.Review;
import com.nebula.api.model.dto.EmailReviewDto;
import com.nebula.api.repository.ReviewRepository;
import com.nebula.api.request_response.CreateReviewRequest;
import com.nebula.api.request_response.CreateReviewResponse;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

	private final RabbitTemplate rabbitTemplate;

	private final ReviewRepository reviewRepository;

	public CreateReviewResponse create(CreateReviewRequest request) {

		final Review review = ReviewMapper.mapToReviewEntity(request);
		reviewRepository.save(review);

		rabbitTemplate.convertAndSend(RabbitConfig.REVIEW_CREATED_QUEUE, ReviewMapper.mapToCreateReviewDto(review));

		return new CreateReviewResponse(review.getId());
	}

	public void updateStatus(Review review) {
		reviewRepository.updateStatus(review.getId(), review.getStatus());
	}

	public List<Review> getAll() {
		return reviewRepository.findAll();
	}

	public void sendEmail(Review review) {
		EmailReviewDto emailReviewDto = ReviewMapper.mapToEmailReviewDto(review);
		rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_QUEUE, emailReviewDto);
	}

	public Review getById(Integer id) {
		Optional<Review> reviewOpt = reviewRepository.findById(id);
		return reviewOpt.orElse(null);
	}
}
