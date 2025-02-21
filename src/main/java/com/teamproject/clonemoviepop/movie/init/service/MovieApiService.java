package com.teamproject.clonemoviepop.movie.init.service;

import com.teamproject.clonemoviepop.movie.entity.Movie;
import com.teamproject.clonemoviepop.movie.service.MovieService;
import com.teamproject.clonemoviepop.tag.entity.Tag;
import com.teamproject.clonemoviepop.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Future;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieApiService {
    private final MovieService movieService;
    private final TagService tageService;

    //영화 전체 정보 가져오기(주간 박스오피스 1달 단위로 9년치)
    public Set<String> getMovieList() {
        HashMap<String, Object> result = new HashMap<>();
        Set<String> movieCodeSet = new HashSet<>();
        List<LinkedHashMap> lmList = new ArrayList<>();

        try {
            List<Future<ResponseEntity<Map>>> futures = new ArrayList<>();

            //예전방식임(구식)
//            SimpleDateFormat formatter = new SimpleDateFormat();
//            formatter.applyPattern("yyyyMMdd");
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(2023, 7, 2);

            //(최신권장방식) DateTimeFormatter, LocalDate는 불변객체라 안전하다~
            // 날짜와 날짜 포멧설정
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate date = LocalDate.of(2023, 8, 2);

            //날짜 형식화
            String formattedDate = date.format(formatter);

            for (int count = 1; count <= 100; count++) {
                //WebClient 생성
                WebClient webClient = WebClient.create("http://kobis.or.kr");

                //Uri 구성
                String url = "/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";

                String uri = UriComponentsBuilder.fromUriString(url)
                        .queryParam("targetDt", formattedDate)
                        .queryParam("weekGb",0)
                        .queryParam("key", "f5eef3421c602c6cb7ea224104795888")
                        .toUriString();

                // Get 요청 동기적 처리
                //여기서 동기적으로 처리해야 하는 이유
                //=> 비동기 처리 시 return값이 빈값일수도 있다.
                String block = webClient.get() //get 요청
                        .uri(uri)
                        .retrieve() // 이 메서드를 통해 응답받는 시작
                        .bodyToMono(String.class) //응답 본문을 String으로 변환. bodyToMono는 비동기적으로 값을 받기 위한 설정입니다.
                        .block();//동기적으로 처리

                // Get 요청 비동기적 처리
//                String block = webClient.get() //get 요청
//                        .uri(uri)
//                        .retrieve() // 이 메서드를 통해 응답받는 시작
//                        .bodyToMono(String.class) //응답 본문을 MONO로 변환 (String형식)
//                        .doOnTerminate(() -> System.out.println("Request completed"))   //비동기적인 요청 처리 종료 시 실행할 작업을 지정
//                        .subscribe(response -> {             // 6. 응답을 비동기적으로 처리
//                          System.out.println(response);    // 응답 내용 출력
//                         }); Mono는 비동기적으로 처리되기 때문에, **구독(subscribe)**을 해야 실제로 실행됩니다. subscribe()는 비동기적으로 응답을 처리하는 콜백을 지정합니다.

            }

        } catch (Exception e) {

        } finally {

        }
        return new HashSet<>();
    }

    // 영화 목록 조회를 통해 가져온 영화 코드들을 가지고 영화 상세정보 API 콜
    public Map<String, Object> getMovieDetail(Set<String> movieCodes) {

        return new HashMap<>();
    }

    public void makeInitMovieData(List<Movie> movieList) {
        movieService.makeInitData(movieList);
    }

    public void makeINitTagData(Set<Tag> tagList) {
        tageService.makeInitData(tagList);
    }
}
