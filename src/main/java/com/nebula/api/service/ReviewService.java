package com.nebula.api.service;

import com.nebula.api.listener.ReviewCreateEvent;
import com.nebula.api.model.Review;
import com.nebula.api.model.dto.CreateReviewDto;
import com.nebula.api.repository.ReviewRepository;
import com.nebula.api.request_response.CreateReviewRequest;
import com.nebula.api.request_response.CreateReviewResponse;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

	private final RabbitTemplate rabbitTemplate;

	private final ReviewRepository reviewRepository;

	private final ApplicationEventPublisher eventPublisher;

	public CreateReviewResponse create(CreateReviewRequest request) {

		final Review review = ReviewMapper.mapToReviewEntity(request);
		reviewRepository.save(review);

		final CreateReviewDto createReviewDto = ReviewMapper.mapToCreateReviewDto(review);
		eventPublisher.publishEvent(new ReviewCreateEvent(this, createReviewDto));

		return new CreateReviewResponse(review.getId());
	}

	public void updateStatus(Review review) {
		reviewRepository.updateStatus(review.getId(), review.getStatus());
	}

	public List<Review> getAll() {
		return reviewRepository.findAll();
	}

	public Review getById(Integer id) {
		Optional<Review> reviewOpt = reviewRepository.findById(id);
		return reviewOpt.orElse(null);
	}
}
