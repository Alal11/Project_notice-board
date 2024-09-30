package mini.noticeboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.noticeboard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/signup")
    public String signupForm(){
        return "signup";
    }

}