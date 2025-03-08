package com.teamproject.clonemoviepop.user.service;

import com.teamproject.clonemoviepop.review_board.entity.ReviewBoard;
import com.teamproject.clonemoviepop.user.entity.ReviewBoardWish;
import com.teamproject.clonemoviepop.user.entity.User;
import com.teamproject.clonemoviepop.user.repository.ReviewBoardWishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ReviewBoardWishService {
    private final ReviewBoardWishRepository reviewBoardWishRepository;

    public boolean isExistReviewBoardWish(ReviewBoard reviewBoard, User user) {
        return reviewBoardWishRepository.existsByReviewBoardAndUser(reviewBoard,user);
    }

    public ReviewBoardWish findReviewBoardAndUser(ReviewBoard reviewBoard, User user) {
        return reviewBoardWishRepository.findByReviewBoardAndUser(reviewBoard,user);
    }
}
