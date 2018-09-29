package com.nebula.api.listener;

import com.nebula.api.model.Review;
import org.springframework.context.ApplicationEvent;

public class SendEmailEvent extends ApplicationEvent {

	private Review review;

	public SendEmailEvent(Object source, Review review) {
		super(source);
		this.review = review;
	}

	public Review getReview() {
		return review;
	}
}
