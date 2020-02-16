package com.udacity.course3.reviews.repository.mongoservice;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@DirtiesContext
public class MongoDBServiceTest {

    @Test
    public void test(@Autowired MongoTemplate mongoTemplate) {

        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("reviewId", 1)
                .add("reviewPoints", 8)
                .add("reviewText", "review test")
                .add("reviewUsername", "alexandre")
                .add("productId", 1)
                .get();

        mongoTemplate.save(objectToSave, "reviews");

        assertThat(mongoTemplate.findAll(DBObject.class, "reviews")).extracting("reviewId")
                .containsOnly(1);
    }

}

