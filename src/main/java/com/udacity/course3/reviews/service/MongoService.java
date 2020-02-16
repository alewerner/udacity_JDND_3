package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.model.mongodb.CommentMongo;
import com.udacity.course3.reviews.model.mongodb.ReviewMongo;
import com.udacity.course3.reviews.repository.mongorepository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoService {

    @Autowired
    private MongoRepository repository;

    /**
     * Save a given review into MongoDb
     * @param review
     */
    public ReviewMongo saveReview(Review review) {
        ReviewMongo reviewMongo = new ReviewMongo(review);

        List<CommentMongo> commentMongoList = getAllCommentsList(review.getComments());
        if (!commentMongoList.isEmpty()) {
            reviewMongo.setCommentMongoDb(commentMongoList);
        }

        return repository.save(reviewMongo);
    }

    /**
     * Save a comment for a given review into MongoDb
     * @param reviewId
     * @param comment
     * @return CommentMongo
     */
    public CommentMongo saveComment(Integer reviewId, Comment comment) {

        ReviewMongo reviewMongo = repository.findByReviewId(reviewId);
        if (reviewMongo == null) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
        // this converts the JPA model to MongoDB
        CommentMongo commentMongo = new CommentMongo(comment);

        List<CommentMongo> commentMongos = new ArrayList<>();
        commentMongos.add(commentMongo);

        if (reviewMongo.getCommentMongoDb() != null && !reviewMongo.getCommentMongoDb().isEmpty()) {
            commentMongos.addAll(reviewMongo.getCommentMongoDb());
        }

        reviewMongo.setCommentMongoDb(commentMongos);
        repository.save(reviewMongo);

        return commentMongo;
    }

    public List<CommentMongo> getAllCommentsList(List<Comment> commentsJpa) {
        List<CommentMongo> commentMongo = new ArrayList<>();
        if (commentsJpa != null && !commentsJpa.isEmpty()) {
            for (Comment comment : commentsJpa) {
                commentMongo.add(new CommentMongo(comment));
            }
        }
        return commentMongo;
    }

    /**
     * Retrieves a list of reviews with the given product id from Mongodb
     * @param productId
     * @return List<ReviewMongo>
     */
    public List<ReviewMongo> findAllByProductId(Integer productId) {
        return repository.findAllByProductId(productId);
    }

    public List<ReviewMongo> findAll(){
        return repository.findAll();
    }

    public List<ReviewMongo> findAllByReviewId(Integer reviewId) {
        return repository.findAllByReviewId(reviewId);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
