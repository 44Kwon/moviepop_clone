package com.teamproject.clonemoviepop.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        @NotBlank
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        @Setter
        private long commentId;
        @NotBlank
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private long commentId;
        private String content;
        private int likes;
        private boolean liked;  //내가 좋아요를 했는지 판별하기 위해
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;
        //userdto도 필요(유저 정보 필요)
    }

    @Getter
    @Setter
    @AllArgsConstructor
    //좋아요 관련 클릭시 갯수만 부분갱신을 위해 필요
    public static class LikeResponse {
        private long commentId;
        private int likes;
    }

    @Getter
    @AllArgsConstructor
    //댓글 수정 후 response
    public static class CommentUpdateResponse {
        private long commentId;
        private String content;
    }
}
