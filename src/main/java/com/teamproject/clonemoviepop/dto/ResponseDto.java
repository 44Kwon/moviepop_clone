package com.teamproject.clonemoviepop.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class ResponseDto {

    public static class SingleResponseDto<T> {
        private T data;
    }

    public static class MultipleResponseDto<T> {
        private List<T> data;
        private PageInfo pageInfo;

        public MultipleResponseDto(List<T> data, Page page) {
            this.data = data;
            this.pageInfo = new PageInfo(page.getNumber()+1, page.getSize(), page.getTotalElements(), page.getTotalPages());
        }
    }

    public static class MultipleInfoResponseDto<T> {
        private T data;
        private PageInfo pageInfo;

        public MultipleInfoResponseDto(T data, Page page) {
            this.data = data;
            this.pageInfo = new PageInfo(page.getNumber() +1, page.getSize(),page.getTotalElements(),page.getTotalPages());
        }
    }

}
