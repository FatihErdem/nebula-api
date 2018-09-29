package com.nebula.api.model.dto;

import com.nebula.api.model.ReviewStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateReviewDto {

	private Integer reviewId;

	private Integer productId;

	private String comment;

	private String reviewerName;

	private LocalDateTime reviewDate;

	private String emailAddress;

	private Integer rating;

	private ReviewStatus status;

}
