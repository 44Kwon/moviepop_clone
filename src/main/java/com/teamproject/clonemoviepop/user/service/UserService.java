package com.teamproject.clonemoviepop.user.service;

import com.teamproject.clonemoviepop.comment.service.CommentService;
import com.teamproject.clonemoviepop.movie_party.service.MoviePartyService;
import com.teamproject.clonemoviepop.review_board.service.ReviewBoardService;
import com.teamproject.clonemoviepop.user.entity.User;
import com.teamproject.clonemoviepop.user.entity.UserTag;
import com.teamproject.clonemoviepop.user.repository.MoviePartyUserRepository;
import com.teamproject.clonemoviepop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReviewBoardWishService reviewBoardWishService;
    private final ReviewBoardService reviewBoardService;
    private final CommentService commentService;
    private final CommentLikeService commentLikeService;
    private final MoviePartyService moviePartyService;
    private final MoviePartyUserRepository moviePartyUserRepository;

//    public User createUser(User user, MultipartFile profileImage) {
//        if(!verifyEmail(user)) {
//            throw new IllegalArgumentException("나중에");
//        }
//        verifyNickname(user.getNickname());
//
//        //UserTag에 user를 넣어줘야한다. (양방향 연관관계 설정)
//        //매퍼에서 해도 된다
//        for (UserTag userTag : user.getUserTags()) {
//
//        }
//    }


    //Email이 특수한지 검증(이미 쓰고 있는 유저가 있는지 검증)
    private boolean verifyEmail(User user) {
        if(userRepository.findByEmail(user.getEmail()) == null) {
            return true;
        }
        return false;
    }

    private void verifyNickname(String nickname) {
        if(userRepository.existsByNickname(nickname))
            throw new IllegalArgumentException("나중에");
    }
}
