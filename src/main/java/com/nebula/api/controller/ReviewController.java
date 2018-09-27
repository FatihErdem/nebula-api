package com.nebula.api.controller;

import com.nebula.api.model.Review;
import com.nebula.api.request_response.CreateReviewRequest;
import com.nebula.api.request_response.CreateReviewResponse;
import com.nebula.api.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping
	public ResponseEntity<CreateReviewResponse> create(@RequestBody @Valid CreateReviewRequest request) {
		CreateReviewResponse response = reviewService.create(request);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping
	public ResponseEntity<List<Review>> tryout() {
		List<Review> allReviews = reviewService.getAll();
		return ResponseEntity.ok(allReviews);
	}

}

