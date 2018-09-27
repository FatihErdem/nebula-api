package com.nebula.api.request_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewResponse implements Serializable {

	private Integer reviewId;

}

