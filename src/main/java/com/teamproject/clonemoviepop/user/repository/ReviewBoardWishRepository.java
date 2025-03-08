package com.teamproject.clonemoviepop.user.repository;

import com.teamproject.clonemoviepop.review_board.entity.ReviewBoard;
import com.teamproject.clonemoviepop.user.entity.ReviewBoardWish;
import com.teamproject.clonemoviepop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewBoardWishRepository extends JpaRepository<ReviewBoard, Long> {
    ReviewBoardWish findByReviewBoardAndUser(ReviewBoard reviewBoard, User user);
    boolean existsByReviewBoardAndUser(ReviewBoard reviewBoard, User user);
}
