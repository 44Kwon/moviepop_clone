package com.teamproject.clonemoviepop.user.dto;

import com.teamproject.clonemoviepop.movie_party.dto.MoviePartyDto;
import com.teamproject.clonemoviepop.review_board.dto.ReviewBoardDto;
import com.teamproject.clonemoviepop.tag.dto.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String name;
    private String nickname;
    private String email;
    private String profileImage;
    private List<TagDto.UserRequest> myTags;
    private List<ReviewBoardDto.UserResponse> myBoard;
    private List<ReviewBoardDto.UserResponse> wishBoard;
    private List<MoviePartyDto.MyPageResponse> myRecruitingGroup;
    private List<MoviePartyDto.MyPageResponse> myParticipatingGroup;
}

