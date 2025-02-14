package com.teamproject.clonemoviepop.utils;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

//UriComponentsBuilder를 사용하여 동적으로 Uri생성
public class UriComponent {
    public static URI createUri(String defaultUrl, long id) {
        return UriComponentsBuilder.newInstance()
                .path(defaultUrl + "/{id}")
                .buildAndExpand(id)
                .toUri();
    }
    //댓글을 작성하려면 필요해서 생성
    public static URI createCommentUri(String defaultUrl, long boardId, long commentId) {
        return UriComponentsBuilder.newInstance()
                .path(defaultUrl + "/{id}")
                .buildAndExpand(boardId, commentId)
                .toUri();
    }

    public static URI createUriWithQuery(String defaultUrl, long id, String name) {
        return UriComponentsBuilder.newInstance()
                .path(defaultUrl + "/{id}")
                .queryParam("name", name) // ?name=값 추가
                .buildAndExpand(id)
                .toUri();
    }
}
