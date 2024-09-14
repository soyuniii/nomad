package com.web2.user;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    // 로그인 , 회원가입 기능을 구현할 예정임


}
