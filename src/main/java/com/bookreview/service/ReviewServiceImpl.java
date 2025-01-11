package com.bookreview.service;

import com.bookreview.entity.Book;
import com.bookreview.entity.Review;
import com.bookreview.exception.BookException;
import com.bookreview.exception.ReviewException;
import com.bookreview.repository.BookRepository;
import com.bookreview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;
    private BookRepository bookRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Review> getReviewsByBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new BookException("Book with ID " + bookId + " not found", HttpStatus.NOT_FOUND));

        List<Review> reviews = reviewRepository.getByBookId(book.getId());
        if (reviews.isEmpty()) {
            throw new ReviewException("No reviews found for Book with ID " + bookId, HttpStatus.NOT_FOUND);
        }

        return reviews;
    }

    @Override
    public Review addReview(Review review) {
        if (review == null) {
            throw new ReviewException("Review cannot be null", HttpStatus.BAD_REQUEST);
        }

        return reviewRepository.save(review);
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new ReviewException("Review with ID " + reviewId + " not found", HttpStatus.NOT_FOUND);
        }

        reviewRepository.deleteById(reviewId);
        return true;
    }
}
