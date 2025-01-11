package com.bookreview.repository;

import com.bookreview.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE b.title = :title")
    boolean existsByTitle(@Param("title") String title);

}
