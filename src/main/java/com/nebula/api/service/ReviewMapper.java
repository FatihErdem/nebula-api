package com.nebula.api.service;

import com.nebula.api.model.EmailType;
import com.nebula.api.model.Review;
import com.nebula.api.model.ReviewStatus;
import com.nebula.api.model.dto.CreateReviewDto;
import com.nebula.api.model.dto.EmailReviewDto;
import com.nebula.api.request_response.CreateReviewRequest;

class ReviewMapper {

	private ReviewMapper() {
	}

	static Review mapToReviewEntity(CreateReviewRequest request) {
		final Review review = new Review();
		review.setProductId(request.getProductId());
		review.setReviewerName(request.getReviewerName());
		review.setEmailAddress(request.getEmailAddress());
		review.setRating(request.getRating());
		review.setComment(request.getComment());
		review.setStatus(ReviewStatus.PENDING);

		return review;
	}

	static CreateReviewDto mapToCreateReviewDto(Review review) {

		CreateReviewDto dto = new CreateReviewDto();
		dto.setReviewId(review.getId());
		dto.setProductId(review.getProductId());
		dto.setReviewerName(review.getReviewerName());
		dto.setEmailAddress(review.getEmailAddress());
		dto.setRating(review.getRating());
		dto.setComment(review.getComment());
		dto.setStatus(review.getStatus());
		return dto;
	}

	static EmailReviewDto mapToEmailReviewDto(Review review) {
		EmailReviewDto dto = new EmailReviewDto();
		dto.setProductId(review.getProductId());
		dto.setReviewerName(review.getReviewerName());
		dto.setEmailAddress(review.getEmailAddress());
		dto.setRating(review.getRating());
		dto.setComment(review.getComment());
		dto.setStatus(review.getStatus());
		dto.setEmailType(EmailType.REVIEW);
		return dto;
	}

}
