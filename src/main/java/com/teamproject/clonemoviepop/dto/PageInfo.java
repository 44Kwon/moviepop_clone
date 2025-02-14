package com.teamproject.clonemoviepop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageInfo {
    private int page;   //현재 페이지 번호
    private int size;   // 한 페이지에 포함된 데이터 수
    private long totalElements; // 전체 데이터 수
    private int totalPages; // 전체 페이지 수
}
