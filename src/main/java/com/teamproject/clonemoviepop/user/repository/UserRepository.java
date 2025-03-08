package com.teamproject.clonemoviepop.user.repository;

import com.teamproject.clonemoviepop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByNickname(String nickname);
}
