package com.udacity.course3.reviews.repository.mongoservice;

import com.mongodb.client.MongoCollection;
import com.udacity.course3.reviews.model.mongodb.CommentMongo;
import com.udacity.course3.reviews.model.mongodb.ProductMongo;
import com.udacity.course3.reviews.model.mongodb.ReviewMongo;
import com.udacity.course3.reviews.repository.mongorepository.MongoRepository;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@DataMongoTest
public class MongoServiceTest {

    private static final int points  = 8;
    private static final String TITLE_REVIEW = "Test Review";
    private static final String REVIEW_TEXT = "Test Comment";
    private static final String USER_NAME = "alexandre";
    private static final String collectionName = "reviews_tests";
    private static final String PRODUCT_NAME = "product test";
    private static final String PRODUCT_DESCRIPTION = "This is the product description for test";
    private static final String COMMENT_DESCRIPTION = "This is the product description for test";
    private static final String COMMENT_TITTLE = "This is the product description for test";

    ReviewMongo reviewMongo;
    CommentMongo commentMongo;
    ProductMongo productMongo;

    @Autowired
    private MongoRepository repository;

    //@Autowired
    //private MongoService mongoService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp(){

        mongoTemplate.dropCollection(collectionName);

        commentMongo = new CommentMongo();
        commentMongo.setCommentCreated_datetime(LocalDateTime.now());
        commentMongo.setCommentId(1);
        commentMongo.setCommentText(COMMENT_DESCRIPTION);
        commentMongo.setCommentTitle(COMMENT_TITTLE);
        commentMongo.setCommentUsername(USER_NAME);

        List<CommentMongo> listComments = new ArrayList<>();
        listComments.add(commentMongo);

        reviewMongo = new ReviewMongo();
        reviewMongo.setReviewId(1);
        reviewMongo.setReviewPoints(8);
        reviewMongo.setReviewCreatedTime(LocalDateTime.now());
        reviewMongo.setReviewTitle(TITLE_REVIEW);
        reviewMongo.setReviewText(REVIEW_TEXT);
        reviewMongo.setReviewUsername(USER_NAME);
        reviewMongo.setCommentMongoDb(listComments);

        List<ReviewMongo> listReview = new ArrayList<>();
        listReview.add(reviewMongo);

        productMongo = new ProductMongo();
        productMongo.setId(1);
        productMongo.setCreatedTime(LocalDateTime.now());
        productMongo.setProductDescription(PRODUCT_DESCRIPTION);
        productMongo.setProductName(PRODUCT_NAME);
        productMongo.setReviewMongos(listReview);

    }

    @After
    public void reset(){
        repository.deleteAll();
        mongoTemplate.dropCollection(collectionName);
    }

    @Test
    public void checkMongoTemplate() {
        assertNotNull(mongoTemplate);
        MongoCollection<Document> createdCollection = mongoTemplate.createCollection(collectionName);
        assertTrue(mongoTemplate.collectionExists(collectionName));
    }

    @Test
    public void doTest() {
        mongoTemplate.save(reviewMongo, collectionName);

        Query query = new Query(new Criteria()
                .andOperator(Criteria.where("reviewId").is("1"),
                        Criteria.where("reviewText").regex(reviewMongo.getReviewText())));

        List<ReviewMongo> reviewMongoList = mongoTemplate.find(query, ReviewMongo.class);

        assertNotNull(reviewMongoList);

    }

}
