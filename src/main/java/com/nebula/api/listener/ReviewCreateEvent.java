package com.nebula.api.listener;

import com.nebula.api.model.dto.CreateReviewDto;
import org.springframework.context.ApplicationEvent;

public class ReviewCreateEvent extends ApplicationEvent {

	private CreateReviewDto createReviewDto;

	public ReviewCreateEvent(Object source, CreateReviewDto dto) {
		super(source);
		this.createReviewDto = dto;
	}

	public CreateReviewDto getCreateReviewDto() {
		return createReviewDto;
	}
}
