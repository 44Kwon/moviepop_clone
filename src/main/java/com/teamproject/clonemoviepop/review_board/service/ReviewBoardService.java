package com.teamproject.clonemoviepop.review_board.service;

import com.teamproject.clonemoviepop.review_board.entity.ReviewBoard;
import com.teamproject.clonemoviepop.review_board.repository.ReviewBoardRepository;
import com.teamproject.clonemoviepop.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewBoardService {

    private final ReviewBoardRepository reviewBoardRepository;

    public ReviewBoard findReviewBoard(User user, Long reviewId) {
        ReviewBoard reviewBoard = findReviewBoardById(reviewId);

        //추가처리 가능성있음

        return reviewBoard;
    }

    @Transactional(readOnly = true)
    public ReviewBoard findReviewBoardById(Long reviewId) {
        Optional<ReviewBoard> optionalReviewBoard = reviewBoardRepository.findById(reviewId);
        ReviewBoard findReviewBoard =
                optionalReviewBoard.orElseThrow(() -> new IllegalArgumentException("없음"));
        return findReviewBoard;
    }


}
