package com.web2.web2.Service;

import com.web2.Exceptions.DuplicateException;
import com.web2.Web2Application;
import com.web2.user.UserService;
import com.web2.user.dto.SignUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 컴포넌트 스캔의 대상은 웹 어플리케이션이 포함된 패키지와 그 하위 모든 패키지로 알고 있음
// 그런데, 테스트 코드 상에서 classes = WebApplication.class 를 사용하지 않으면 생성자를 통한 주입이 불가능했음

@SpringBootTest(classes = Web2Application.class) // 뭐지..? 컴포넌트 스캔의 대상으로
public class UserServiceTest {


    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void 회원가입() throws Exception{
        // when
        SignUser user = new SignUser("xodd","22@naver.com","0000","이탈리아",45);

        // given
        DuplicateException e = assertThrows(DuplicateException.class, () -> {userService.sign(user);});
        // 로그인할 때 발생하는 중복 예외를 발생시킴
        assertThat(e.getMessage()).isEqualTo("Email already exists");
        // then

    }




}
