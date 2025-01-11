package com.bookreview.service;


import com.bookreview.entity.Book;
import com.bookreview.entity.Review;
import com.bookreview.exception.BookException;
import com.bookreview.repository.BookRepository;
import com.bookreview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if(book.isEmpty()){
            throw new BookException("Book with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }

        return book.get();
    }

    @Override
    public Book createBook(Book book) {
        if(bookRepository.existsByTitle(book.getTitle())){
            throw new BookException("Book with title " + book.getTitle() + " already exists", HttpStatus.CONFLICT);
        }

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isEmpty()) {
            throw new BookException("Book with ID " + id + " not found, cannot update", HttpStatus.NOT_FOUND);
        }

        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public boolean deleteBook(Long id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isEmpty()) {
            throw new BookException("Book with ID " + id + " not found, cannot delete", HttpStatus.NOT_FOUND);
        }

        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public double getAverageRating(Long id) {
        List<Review> reviews = reviewRepository.getByBookId(id);

        if (reviews.isEmpty()) {
            throw new BookException("No reviews found for Book with ID " + id, HttpStatus.NOT_FOUND);
        }

        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
