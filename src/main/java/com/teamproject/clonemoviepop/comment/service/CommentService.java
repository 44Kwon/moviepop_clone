package com.teamproject.clonemoviepop.comment.service;

import com.teamproject.clonemoviepop.comment.dto.CommentDto;
import com.teamproject.clonemoviepop.comment.entity.Comment;
import com.teamproject.clonemoviepop.comment.mapper.CommentMapper;
import com.teamproject.clonemoviepop.comment.repository.CommentRepository;
import com.teamproject.clonemoviepop.dto.ResponseDto;
import com.teamproject.clonemoviepop.review_board.entity.ReviewBoard;
import com.teamproject.clonemoviepop.review_board.repository.ReviewBoardRepository;
import com.teamproject.clonemoviepop.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ReviewBoardRepository reviewBoardRepository;

    public Long createComment(Long reviewId, User user, CommentDto.Post postDto) {
        Comment postComment = commentMapper.commentPostDtoToComment(postDto);
        Optional<ReviewBoard> optionalReviewBoard = reviewBoardRepository.findById(reviewId);
        ReviewBoard reviewBoard = optionalReviewBoard.orElseThrow(() -> new IllegalArgumentException("게시글 없음 오류"));

        postComment.setReviewBoard(reviewBoard);
        //연관관계 편의메서드
        postComment.setUser(user);

        Comment comment = commentRepository.save(postComment);

        return comment.getCommentId();
    }

    public CommentDto.CommentUpdateResponse updateComment(User user, CommentDto.Patch patchDto) {
        Comment patchComment = commentMapper.commentPatchDtoToComment(patchDto);
        Comment findComment = findVerifiedCommentId(patchComment.getCommentId());

        //나중에 고쳐야함. 내가 작성한 댓글이 맞는지 검증하는 로직
        if(findComment.getUser().getEmail().equals(user.getEmail())) {
            throw new IllegalArgumentException("고쳐야함");
        }

        //ofNullable => 값이 null이면 빈 Optional 객체를 생성하고,
        //null이 아니면 해당 값을 래핑한 Optional 객체를 생성합니다.
        //ifPresent()는 Optional 객체 안에 값이 존재할 경우에만 특정 작업을 수행하는 메서드입니다.
        //더티 체킹을 통해 자동으로 쿼리 나감
        Optional.ofNullable(patchComment.getContent())
                .ifPresent(content -> findComment.updateComment(content));

        return commentMapper.commentToCommentPatchResponseDto(findComment);
    }

    public CommentDto.Response findComment(long commentId) {
        return commentMapper.commentToCommentResponseDto(findVerifiedCommentId(commentId));
    }

    //댓글 페이징
    @Transactional(readOnly = true)
    public ResponseDto.MultipleResponseDto<Comment> findComments(int page, int size) {
        Page<Comment> pageComment = commentRepository.findAll(PageRequest.of(
                page - 1, size, Sort.by(Sort.Order.desc("commentId"))));

        List<Comment> comments = pageComment.getContent();

        return new ResponseDto.MultipleResponseDto<>(comments, pageComment);
    }


    public void deleteComment(User user, Long commentId) {
        Comment comment = findVerifiedCommentId(commentId);
        if (!comment.getUser().getEmail().equals(user.getEmail())) {
            throw new IllegalArgumentException("아이디 안맞음 나중에");
        }

        //orphanremoval을 이용한 삭제(되는지 모름)
        ReviewBoard reviewBoard = comment.getReviewBoard();
        comment.removeComment(user, reviewBoard);

        //repository에서 delete를 이용해 삭제
        //만약 위가 동작한다면 중복 쿼리 발생
        commentRepository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    private Comment findVerifiedCommentId(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment comment = optionalComment.orElseThrow(() -> new IllegalArgumentException("나중에"));

        return comment;
    }
}
