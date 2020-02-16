package com.udacity.course3.reviews.repository.mongorepository;

import com.udacity.course3.reviews.model.mongodb.ReviewMongo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<ReviewMongo, String> {

    List<ReviewMongo> findAllByProductId(Integer productId);

    List<ReviewMongo> findAllByReviewId(Integer reviewId);

    ReviewMongo findByProductId(Integer productId);

    ReviewMongo findByReviewId(Integer reviewId);
}
