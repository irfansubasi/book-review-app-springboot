package com.bookreview.service;

import com.bookreview.entity.Review;

import java.util.List;

public interface ReviewService {

    //get all reviews by book
    List<Review> getReviewsByBook(Long bookId);

    //create review
    Review addReview(Review review);

    //delete review
    boolean deleteReview(Long reviewId);

}
