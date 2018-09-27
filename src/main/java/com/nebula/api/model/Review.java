package com.nebula.api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "productreview", schema = "production")
public class Review implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "productreviewid")
	private Integer id;

	@Column(name = "productid")
	private Integer productId;

	@Column(name = "reviewername")
	private String reviewerName;

	@Column(name = "reviewdate")
	private LocalDateTime reviewDate = LocalDateTime.now();

	@Column(name = "emailaddress")
	private String emailAddress;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "status")
	private ReviewStatus status;

	@Column(name = "comments")
	private String comment;

	@Column(name = "modifieddate")
	private LocalDateTime modifiedName = LocalDateTime.now();

}
