package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@Validated
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Creates a product.
     *
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     * @param product
     * @return product
     *
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates a product from a valid RequestBody")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {

        if (product.getCreatedTime() == null) {
            product.setCreatedTime(LocalDateTime.now());
        }

        Product result = productRepository.save(product);

        return ResponseEntity.ok(result);
    }

    /**
     * Finds a product by id.
     *
     * @param id
     * @return product
     */
    @RequestMapping(value = "/{id}")
    @ApiOperation(value = "Find a product for a given Id")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            return  ResponseEntity.ok(optionalProduct);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Returns a list of products")
    public ResponseEntity<List<?>> listProducts() {
        List<Product> productList = productRepository.findAll();

        return ResponseEntity.ok(productList);
    }
}