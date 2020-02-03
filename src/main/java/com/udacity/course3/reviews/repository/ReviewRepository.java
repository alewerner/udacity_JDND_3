package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    /**
     * This method returns all reviews of a given Id Product
     *
     * @param productId
     * @return List<Review>
     */
    List<Review> findByProductId(Integer productId);
}
