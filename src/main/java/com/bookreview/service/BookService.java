package com.bookreview.service;

import com.bookreview.entity.Book;

import java.util.List;

public interface BookService {

    //list all books
    List<Book> getAllBooks();

    //show book by id
    Book getBookById(Long id);

    //create book
    Book createBook(Book book);

    //update book
    Book updateBook(Long id, Book book);

    //delete book
    boolean deleteBook(Long id);

}
