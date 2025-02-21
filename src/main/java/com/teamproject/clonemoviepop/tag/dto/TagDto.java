package com.teamproject.clonemoviepop.tag.dto;

import lombok.Getter;
import lombok.Setter;

public class TagDto {

    @Getter
    @Setter
    public static class Response {
        private Long tagId;
        private String tagName;
    }

    @Getter
    @Setter
    public static class ReviewBoardRequest {
        private Long tagId;
    }

    @Getter
    @Setter
    public static class UserRequest {
        private Long tagId;
        private String tagName;
    }
}
