CREATE TABLE product (
    id INT NOT NULL AUTO_INCREMENT,
    name varchar(255) not null,
    description VARCHAR(500),
    created_time TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE review (
    id_review INT NOT NULL AUTO_INCREMENT,
    title varchar(100) not null,
    review_text VARCHAR(10000) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    created_ts TIMESTAMP NOT NULL,
    product_id INT not null,
    
    PRIMARY KEY (id_review),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE comment (
    id_comment INT NOT NULL AUTO_INCREMENT,
    comment_title varchar(100),
    comment_text VARCHAR(1000) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    comment_ts TIMESTAMP NOT NULL,
    id_review INT,
    PRIMARY KEY (id_comment),
    FOREIGN KEY (id_review) REFERENCES review(id_review)
);