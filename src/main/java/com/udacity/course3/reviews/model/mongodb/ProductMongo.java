package com.udacity.course3.reviews.model.mongodb;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ProductMongo {

    @Id
    private int id;
    private String productName;
    private String productDescription;
    private LocalDateTime createdTime;
    private List<ReviewMongo> reviewMongos;

    public ProductMongo(int id, String productName, String productDescription, LocalDateTime createdTime, List<ReviewMongo> reviewMongos) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.createdTime = createdTime;
        this.reviewMongos = reviewMongos;
    }
}
