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

    public static URI createUriWithQuery(String defaultUrl, long id, String name) {
        return UriComponentsBuilder.newInstance()
                .path(defaultUrl + "/{id}")
                .queryParam("name", name) // ?name=값 추가
                .buildAndExpand(id)
                .toUri();
    }
}
