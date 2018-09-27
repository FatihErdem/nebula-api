package com.nebula.api.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ReviewStatusConverter implements AttributeConverter<ReviewStatus, String> {

	@Override
	public String convertToDatabaseColumn(ReviewStatus reviewStatus) {
		return reviewStatus.getStatus();
	}

	@Override
	public ReviewStatus convertToEntityAttribute(String s) {
		return ReviewStatus.fromShortStatus(s);
	}
}
