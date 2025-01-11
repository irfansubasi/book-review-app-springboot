package com.bookreview.repository;

import com.bookreview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> getByBookId(Long bookId);

}
