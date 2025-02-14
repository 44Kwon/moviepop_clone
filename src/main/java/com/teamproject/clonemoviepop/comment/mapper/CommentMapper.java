package com.teamproject.clonemoviepop.comment.mapper;


import com.teamproject.clonemoviepop.comment.dto.CommentDto;
import com.teamproject.clonemoviepop.comment.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") //componentModel = "spring" 이 옵션은 매퍼를 Spring 빈으로 등록해준다
public interface CommentMapper {
    Comment commentPostDtoToComment(CommentDto.Post postDto);
    Comment commentPatchDtoToComment(CommentDto.Patch patchDto);
    CommentDto.CommentUpdateResponse commentToCommentPatchResponseDto(Comment comment);
    CommentDto.Response commentToCommentResponseDto(Comment comment);
    List<CommentDto.Response> commentsToCommentResponseDtos(List<Comment> comments);
    CommentDto.LikeResponse commentToCommentLikeResponse(Comment comment);
}
