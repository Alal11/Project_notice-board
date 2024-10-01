package mini.noticeboard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.noticeboard.dto.UserDTO;
import mini.noticeboard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    // 생성자 주입
    private final UserService userService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/signup")
    public String signupForm(){
        return "signup";  // 화면만 띄워줌
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDTO userDTO){
        System.out.println("userDTO = " + userDTO);
        userService.save(userDTO);  // db 처리가 필요함
        return "signin";
    }

    @GetMapping("/signin")
    public String signinForm(){
        return "signin";
    }

    @PostMapping("/signin")
    public String signin(@ModelAttribute UserDTO userDTO, HttpSession session){
        UserDTO signinResult = userService.login(userDTO);
        if(signinResult != null){
            // 로그인 성공
            session.setAttribute("loginName", signinResult.getUserName());
            return "main";
        }else{
            // 로그인 실패
            return "signin";
        }
    }




}
