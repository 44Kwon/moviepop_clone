package com.teamproject.clonemoviepop.utils;

import com.teamproject.clonemoviepop.user.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

//User의 나이를 구하는 클래스 => User클래스 안에 들어가는게 맞는거 아닌가?
public class UserUtils {
    public static Period getAge(User user) {
        LocalDate birth = user.getBirth();
        LocalDateTime now = LocalDateTime.now();

        return Period.between(birth, now.toLocalDate());
    }
}
