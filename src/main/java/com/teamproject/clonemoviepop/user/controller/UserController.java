package com.teamproject.clonemoviepop.user.controller;

import com.teamproject.clonemoviepop.comment.mapper.CommentMapper;
import com.teamproject.clonemoviepop.movie_party.mapper.MoviePartyMapper;
import com.teamproject.clonemoviepop.review_board.mapper.ReviewBoardMapper;
import com.teamproject.clonemoviepop.review_board.service.ReviewBoardService;
import com.teamproject.clonemoviepop.tag.mapper.TagMapper;
import com.teamproject.clonemoviepop.tag.service.TagService;
import com.teamproject.clonemoviepop.user.dto.UserDto;
import com.teamproject.clonemoviepop.user.mapper.UserMapper;
import com.teamproject.clonemoviepop.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@Validated
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final static String USER_DEFAULT_URI = "/users";
    private final UserService userService;
    private final ReviewBoardService reviewBoardService;
    private final UserMapper userMapper;
    private final ReviewBoardMapper reviewBoardMapper;
    private final CommentMapper commentMapper;
    private final TagMapper tagMapper;
    private final TagService tagService;
    private final MoviePartyMapper moviePartyMapper;
//    private final JwtTokenizer jwtTokenizer;
//    private final ImageUtil imageUtil;

    //@RequestBody를 쓰지않으면 Application_json_value는 필요없음
    //consumes 옵션은 클라이언트가 서버에 보낼 데이터 형식을 지정. 즉, 서버에서 어떤 데이터를 받을지 결정
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity postUser(@Valid @RequestPart UserDto.Post userPostDto,
                                   @RequestPart(required = false) MultipartFile profileImage) {
        return ResponseEntity.ok("asd");
    }
}
