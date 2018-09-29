package com.nebula.api.model.dto;

import com.nebula.api.model.ReviewStatus;
import lombok.Data;

@Data
public class UpdateReviewDto {

	private Integer id;

	private ReviewStatus status;
}
