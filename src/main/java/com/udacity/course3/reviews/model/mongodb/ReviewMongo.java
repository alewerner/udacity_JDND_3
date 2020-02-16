package com.udacity.course3.reviews.model.mongodb;

import com.udacity.course3.reviews.model.Review;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "reviews")
public class ReviewMongo {

    @Id
    private int reviewId;
    private String reviewTitle;
    private String reviewText;
    private String reviewUsername;
    private LocalDateTime reviewCreatedTime;
    private int reviewPoints;
    private int productId;
    private ProductMongo productMongo;
    private List<CommentMongo> commentMongoDb;

    public ReviewMongo(Review review) {
        this.reviewId = review.getReviewId();
        this.reviewCreatedTime = review.getReviewCreatedTime();
        this.reviewPoints = review.getReviewPoints();
        this.reviewText = review.getReviewText();
        this.reviewUsername = review.getReviewUsername();
        this.reviewTitle = review.getReviewTitle();
        this.productId = review.getProduct().getId();
    }

    public ReviewMongo(int reviewId, String reviewTitle, String reviewText, String reviewUsername, LocalDateTime reviewCreatedTime, int reviewPoints, ProductMongo productMongo, List<CommentMongo> commentMongoDb, int producId) {
        this.reviewId = reviewId;
        this.reviewTitle = reviewTitle;
        this.reviewText = reviewText;
        this.reviewUsername = reviewUsername;
        this.reviewCreatedTime = reviewCreatedTime;
        this.reviewPoints = reviewPoints;
        this.productMongo = productMongo;
        this.commentMongoDb = commentMongoDb;
        this.productId = productId;
    }
}
