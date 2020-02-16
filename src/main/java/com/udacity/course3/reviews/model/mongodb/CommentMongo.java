package com.udacity.course3.reviews.model.mongodb;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Review;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CommentMongo {

    @Id
    private int commentId;
    private String commentTitle;
    private String commentText;
    private String commentUsername;
    private LocalDateTime commentCreated_datetime;
    private Review review;

    public CommentMongo(Comment comment) {
        this.commentCreated_datetime = comment.getCommentCreated_datetime();
        this.commentId = comment.getCommentId();
        this.commentText = comment.getCommentText();
        this.commentUsername = comment.getCommentUsername();
        this.commentTitle = comment.getCommentTitle();
    }

    public CommentMongo(int commentId, String commentTitle, String commentText, String commentUsername, LocalDateTime commentCreated_datetime, Review review) {
        this.commentId = commentId;
        this.commentTitle = commentTitle;
        this.commentText = commentText;
        this.commentUsername = commentUsername;
        this.commentCreated_datetime = commentCreated_datetime;
        this.review = review;
    }
}
