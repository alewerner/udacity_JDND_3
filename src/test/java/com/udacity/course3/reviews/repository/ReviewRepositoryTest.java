package com.udacity.course3.reviews.repository;

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
    private TestEntityManager testEntityManager;

    static final String review_text = "Review very good";
    static final String review_user_name = "Alexandre";
    static final String review_title = "Review one";
    static final int review_points = 10;

    @Before
    public void setup() {
        Review review = new Review();
        review.setReviewCreatedTime(LocalDateTime.now(Clock.systemUTC()));
        review.setReviewText(review_text);
        review.setReviewUsername(review_user_name);
        review.setReviewTitle(review_title);
        review.setReviewPoints(review_points);

        testEntityManager.persist(review);
    }

    @Test
    public void assertFindAll() {
        int reviewListSize = 1;

        List<Review> actual = repository.findAll();
        assertThat(actual).isNotNull();
        assertEquals(reviewListSize, actual.size());
    }

    @Test
    public void assertFindById() {
        Review actual = repository.findById(1).get();
        assertThat(actual).isNotNull();
        assertEquals(review_title, actual.getReviewTitle());
    }

    @Test
    public void assertSaveReview() {

        Review review = repository.findById(1).get();
        review.setReviewPoints(8);

        Review actual = repository.save(review);

        assertEquals(review.getReviewPoints(), actual.getReviewPoints());
    }

    @Test
    public void assertDeleteReview() {

        Review review = repository.findById(1).get();

        repository.delete(review);

        List<Review> actual = repository.findAll();
        assertEquals(0, actual.size());
    }
}
