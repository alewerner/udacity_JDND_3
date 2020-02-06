package com.udacity.course3.reviews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private int reviewId;

    @Column(name = "title")
    @NotNull
    @NotEmpty(message = "Please provide a review title")
    private String reviewTitle;

    @Column(name = "reviewText")
    @NotNull
    @NotEmpty(message = "Please provide a review text")
    private String reviewText;

    @Column(name = "user_name")
    @NotNull
    @NotEmpty(message = "Please provide a review user name")
    private String reviewUsername;

    @Column(name = "created_ts")
    private LocalDateTime reviewCreatedTime;

    @Column(name = "review_points")
    @NotNull(message = "Please provide points")
    private int reviewPoints;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @OneToMany(mappedBy = "review", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Comment> comments;

}
