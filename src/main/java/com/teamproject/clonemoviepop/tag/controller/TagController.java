package com.teamproject.clonemoviepop.tag.controller;

import com.teamproject.clonemoviepop.dto.ResponseDto;
import com.teamproject.clonemoviepop.tag.dto.TagDto;
import com.teamproject.clonemoviepop.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Validated
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity getAllTags() {
        List<TagDto.Response> tags = tagService.getTags();
        return new ResponseEntity(new ResponseDto.SingleResponseDto<>(tags),
                HttpStatus.OK);
    }
}
