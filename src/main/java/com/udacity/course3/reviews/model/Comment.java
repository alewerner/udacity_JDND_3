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

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private int commentId;

    @Column(name = "comment_title")
    private String commentTitle;

    @Column(name = "comment_text")
    @NotEmpty(message = "Please provide a comment text")
    @NotNull
    private String commentText;

    @Column(name = "user_name")
    @NotEmpty(message = "Please provide a user name")
    @NotNull
    private String commentUsername;

    @Column(name = "comment_ts")
    private LocalDateTime commentCreated_datetime;

    @ManyToOne
    @JoinColumn(name = "id_review")
    @JsonIgnore
    private Review review;

}
