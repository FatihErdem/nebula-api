package com.nebula.api.model.dto;

import com.nebula.api.model.EmailType;
import com.nebula.api.model.ReviewStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmailReviewDto {

	private EmailType emailType;

	private Integer productId;

	private String comment;

	private String reviewerName;

	private LocalDateTime reviewDate;

	private String emailAddress;

	private Integer rating;

	private ReviewStatus status;
}
