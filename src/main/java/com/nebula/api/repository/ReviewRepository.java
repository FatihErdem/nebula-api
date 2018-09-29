package com.nebula.api.repository;

import com.nebula.api.model.Review;
import com.nebula.api.model.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Review SET status = :status WHERE id = :id")
	void updateStatus(@Param("id") Integer id, @Param("status") ReviewStatus status);
}
