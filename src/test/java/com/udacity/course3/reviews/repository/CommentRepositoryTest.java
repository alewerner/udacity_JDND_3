package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.model.Comment;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This Class tests the behavior of the Comment Repository
 *
 * Because this class tests only the Repository, only happy case tests are added
 *
 * To avoid errors, the context is being cleaned for every test
 * References: https://www.baeldung.com/spring-dirtiescontext
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setup() {
        Comment comment = new Comment();

        comment.setCommentTitle("Very good product!");
        comment.setCommentText("This product is very good! I Recomend it!");
        comment.setCommentUsername("Alexandre");
        comment.setCommentCreated_datetime((LocalDateTime.now()));

        this.testEntityManager.persist(comment);
    }

    @Test
    public void assertFindAll() {
        int listSize = 1;

        List<Comment> actual = repository.findAll();

        Assertions.assertThat(actual).isNotNull();
        assertEquals(listSize, actual.size());
    }

    @Test
    public void asserFindById() {
        int idComment = 1;

        Comment actual = repository.findById(idComment).get();
        Assertions.assertThat(actual).isNotNull();
        assertEquals("This product is very good! I Recomend it!", actual.getCommentText());
        assertEquals("Alexandre", actual.getCommentUsername());
    }

    @Test
    public void assertSaveComment() {
        int idComment = 1;

        Comment comment = repository.findById(idComment).get();
        comment.setCommentUsername("Marina");
        comment.setCommentTitle("Disappointed");
        comment.setCommentUsername("This product didn't worked at it was expected. I not recommend it.");

        Comment actual = repository.save(comment);

        assertEquals(comment.getCommentText(), actual.getCommentText());
        assertEquals(comment.getCommentUsername(), actual.getCommentUsername());
        assertEquals(comment.getCommentTitle(), actual.getCommentTitle());
    }

    @Test
    public void assertCommentIsDeleted() {

        Comment comment = repository.findById(1).get();

        repository.delete(comment);

        List<Comment> actual = repository.findAll();
        assertEquals(0, actual.size());
    }

}
