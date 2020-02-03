package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.model.Product;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * This Class tests the behavior of the Product Repository
 *
 * Because this class tests only the Repository, only happy case tests are added
 *
 * To avoid errors, the context is being cleaned for every test
 * References: https://www.baeldung.com/spring-dirtiescontext
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    static final String product_name = "Product 1";
    static final String product_description = "This Product is very useful";

    /**
     * Creates pre-requisites for testing, such as an example product
     * and persist it on a H2 database in memory.
     */
    @Before
    public void setup() {
        Product product = new Product();
        product.setProductDescription(product_description);
        product.setCreatedTime(LocalDateTime.now());
        product.setProductName(product_name);

        testEntityManager.persist(product);
    }

    /**
     * Tests for successful find all products in database
     */
    @Test
    public void asserFindAll() {
        int productListSize = 1;

        List<Product> actual = repository.findAll();
        assertThat(actual).isNotNull();
        assertEquals(productListSize, actual.size());
    }

    /**
     * Tests for successful find a product for a given Id
     */
    @Test
    public void asserFindById() {

        Product actual = repository.findById(1).get();
        assertThat(actual).isNotNull();
        assertEquals(product_name, actual.getProductName());
    }

    /**
     * Tests for successful save product in the database
     */
    @Test
    public void assertSaveProduct() {
        Product product = repository.findById(1).get();
        product.setProductDescription("This product is awesome!");

        Product actual = repository.save(product);

        assertEquals(product.getProductDescription(), actual.getProductDescription());
    }

    /**
     * Tests for successful deletion of a product
     */
    @Test
    public void assertDeleteProduct() {
        Product product = repository.findById(1).get();

        repository.delete(product);
        List<Product> actual = repository.findAll();
        assertEquals(0, actual.size());
    }
    
}
