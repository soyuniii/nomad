package com.web2.user;

import com.web2.global.exception.AuthenticationException;
import com.web2.global.exception.DuplicateException;
import com.web2.review.Review;
import com.web2.user.dto.LoginUser;
import com.web2.user.dto.SignUser;
import com.web2.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    public final UserRepository userRepository;

    // 회원가입 시 중복된 이메일, 이름을 검사
    // 로그인 시에는 저장된 이메일과 패스워드 쌍이 일치하는 지 확인

    //회원가입 중복된 이메일, 이름을 검사하고 예외가 발생하지 않는다면 저장
    public String sign(SignUser Dto) throws DuplicateException {

        checkNickname(Dto);
        checkEmail(Dto);
        createSignUser(Dto);
        return "회원가입 성공";
    }

    // 로그인 기능 데이터베이스에 저장된 데이터와 DTO로 입력받은 데이터를 비교하여 로그인 성공 여부를 판단
    // 세션을 통한 로그인 기능을 구현할 것임, 그러므로 세션 ID를 넘겨주는 로직을 추가해야함
    public String login(LoginUser Dto) {
        Optional<User> value = userRepository.findByEmailAndPassword(Dto.email(), Dto.password());
        if (value.isPresent()) {
            return "로그인 성공";
        } else
            throw new AuthenticationException("인증에 실패했습니다");
    }

    //추가된 프로필 조회 메서드
    public UserDTO getprofile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));

        List<Review> reviews = user.getReviewList();
        int reviewCount = reviews.size();

        return new UserDTO(
                user.getNickname(),
                user.getNationality(),
                user.getAge(),
                reviewCount
        );
    }

    // 중복된 닉네임을 검사
    public void checkNickname(SignUser Dto) throws DuplicateException {
        Optional<User> value = userRepository.findByNickname(Dto.nickname());

        if (value.isPresent()) {
            throw new DuplicateException("nickname already exists");
        }
    }

    // 중복된 이메일을 검사
    public void checkEmail(SignUser Dto) throws DuplicateException {
        Optional<User> value = userRepository.findByEmail(Dto.email());

        if (value.isPresent()) {
            throw new DuplicateException("Email already exists");
        }
    }

    // 가입된 사용자를 데이터베이스에 저장하는 로직, 엔티티 변환과 분리함
    public void createSignUser(SignUser Dto) {
        User entity = toEntity(Dto);
        userRepository.save(entity);
    }

    //DTO로 전달받은 값을 엔티티로 변환
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

    public User findUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get(); //사용자 존재 시 User 객체 반환
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}

