package com.nebula.api.request_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest implements Serializable {

	@NotNull
	private Integer productId;

	@NotEmpty
	private String reviewerName;

	@NotEmpty
	private String emailAddress;

	@NotNull
	private Integer rating;

	@NotEmpty
	private String comment;
}
