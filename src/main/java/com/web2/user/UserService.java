package com.web2.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {

    public final UserRepository userRepository;

    // 이메일은 중복을 방지해야함
    // 중복을 방지하는 메소드, DTO를 엔티티로 엔티티를 DTO로 만드는 메소드

    // 엔티티로부터 값을 가져와서 저장

    //중복되는 이메일 여부를 확인하고 중복시 예외를 던지는 로직


















    public SignUser signDto(User entity) {

        SignUser Dto = new SignUser(
                entity.getNickname(),
                entity.getEmail(),
                entity.getPassword());

        return Dto;
    }
    public LoginUser loginDto(User entity) {
        LoginUser Dto = new LoginUser(
                entity.getEmail(),
                entity.getPassword());

        return Dto;
    }

    public User toEntity(SignUser Dto) {
        User entity = new User();
        entity.setNickname(Dto.nickname());
        entity.setEmail(Dto.email());
        entity.setPassword(Dto.password());
        userRepository.save(entity);
        return entity;
    }
    public User toEntity(LoginUser Dto) {
        User entity = new User();
        entity.setEmail(Dto.email());
        entity.setPassword(Dto.password());
        userRepository.save(entity);
        return entity;
    }



}

