package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * This Class tests the behavior of the Review Repository
 *
 * Because this class tests only the Repository, only happy case tests are added
 *
 * To avoid errors, the context is being cleaned for every test
 * References: https://www.baeldung.com/spring-dirtiescontext
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    static final String review_text = "Review very good";
    static final String review_user_name = "Alexandre";
    static final String review_title = "Review one";
    static final int review_points = 10;

    /**
     * Creates pre-requisites for testing, such as an example review, comment and product
     * and persist it on a H2 database in memory..
     */
    @Before
    public void setup() {
        Review review = new Review();
        review.setReviewCreatedTime(LocalDateTime.now(Clock.systemUTC()));
        review.setReviewText(review_text);
        review.setReviewUsername(review_user_name);
        review.setReviewTitle(review_title);
        review.setReviewPoints(review_points);

        testEntityManager.persist(review);

        Product product = new Product();
        product.setProductDescription("Prod 1 is good for testing");
        product.setProductName("Prod 1");
        product.setCreatedTime(LocalDateTime.now());

        testEntityManager.persist(product);

        Comment comment = new Comment();
        comment.setCommentText("Prod 1 solve my problem!");
        comment.setCommentUsername("Alexandre");
        comment.setCommentCreated_datetime(LocalDateTime.now());
        comment.setCommentTitle("Prod 1");

        testEntityManager.persist(comment);
    }

    /**
     * Tests for successful find all reviews in the system
     */
    @Test
    public void assertFindAll() {
        int reviewListSize = 1;

        List<Review> actual = repository.findAll();
        assertThat(actual).isNotNull();
        assertEquals(reviewListSize, actual.size());
    }

    /**
     * Tests for successful find a review for a given Id
     */
    @Test
    public void assertFindById() {
        Review actual = repository.findById(1).get();
        assertThat(actual).isNotNull();
        assertEquals(review_title, actual.getReviewTitle());
    }

    /**
     * Tests for successful save review in the database
     * with comment and product
     */
    @Test
    public void assertSaveReview() {

        Review review = repository.findById(1).get();
        review.setReviewPoints(8);

        Product product = productRepository.findById(1).get();

        review.setProduct(product);

        Comment comment = commentRepository.findById(1).get();
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);

        review.setComments(commentList);

        Review actual = repository.save(review);

        assertEquals(review.getReviewPoints(), actual.getReviewPoints());
        assertEquals(review.getProduct(), actual.getProduct());
        assertEquals(review.getComments().size(), 1);
    }

    /**
     * Tests for successful deletion of a review
     */
    @Test
    public void assertDeleteReview() {

        Review review = repository.findById(1).get();

        repository.delete(review);

        List<Review> actual = repository.findAll();
        assertEquals(0, actual.size());
    }
}
