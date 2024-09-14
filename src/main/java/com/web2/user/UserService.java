package com.web2.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.web2.user.DuplicateEmailException;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    public final UserRepository userRepository;


    // 회원가입 시 중복된 이메일, 이름을 검사
    // 로그인 시에는 저장된 이메일과 패스워드 쌍이 일치하는 지 확인

    //회원가입 중복된 이메일, 이름을 검사하고 예외가 발생하지 않는다면 저장
    public String sign(SignUser Dto) throws DuplicateEmailException {
        try{
            checkNickname(Dto);
            checkEmail(Dto);
            createSignUser(Dto);
            return "회원가입 성공";
        }catch(DuplicateEmailException e){
            return e.getMessage();
        }

    }
    // 로그인 기능 일단 세션과 JWT 토큰을 고려하지 않고 만들어만 뒀음
    // 이메일 및 비밀번호 둘중에 올바르지 않은 값을 입력한 경우도 처리하는 것이 좋을 거 같음
    public String login(LoginUser Dto){
        Optional<User> value = userRepository.findByEmailAndPassword(Dto.email(), Dto.password());
        Optional<User> value2 = userRepository.findByEmailOrPassword(Dto.email(), Dto.password());

        if(value.isPresent()){
            return "로그인 성공";
        }else if (value2.isPresent()){
            return "이메일과 비밀번호를 다시 확인해주세요";
        }else
            return "가입되지 않은 회원입니다. 가입먼저 진행해주세요";
    }

    public void checkUser(LoginUser Dto){

    }


    // 중복된 닉네임을 검사
    public void checkNickname(SignUser Dto) throws DuplicateEmailException {
        Optional<User> value = userRepository.findByNickname(Dto.nickname());

        if(value.isPresent()) {
            throw new DuplicateEmailException("nickname already exists");
        }
    }
    // 중복된 이메일을 검사 
    public void checkEmail(SignUser Dto) throws DuplicateEmailException {
        Optional<User> value = userRepository.findByEmail(Dto.email());

        if(value.isPresent()) {
            throw new DuplicateEmailException("Email already exists");
        }
    }

    // 가입된 사용자를 데이터베이스에 저장하는 로직, 엔티티 변환과 분리함
    public void createSignUser(SignUser Dto) {
        User entity = toEntity(Dto);
        userRepository.save(entity);
    }

    public User toEntity(SignUser Dto) {
        User entity = new User();
        entity.setNickname(Dto.nickname());
        entity.setEmail(Dto.email());
        entity.setPassword(Dto.password());
        entity.setNationality(Dto.nationality());
        entity.setIs_vegetarian(Dto.is_vegetarian());
        entity.setAge(Dto.age());
        return entity;
    }
    public User toEntity(LoginUser Dto) {
        User entity = new User();
        entity.setEmail(Dto.email());
        entity.setPassword(Dto.password());
        return entity;
    }



}

