package com.web2.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {

    public final UserRepository userRepository;

    // 이메일은 중복을 방지해야함
    // 중복을 방지하는 메소드, DTO를 엔티티로 엔티티를 DTO로 만드는 메소드

    public SignUser convertDtoSign(User entity) {
        return // signUser DTO의 객체를 반환해야할 거 같은데
    }






}

