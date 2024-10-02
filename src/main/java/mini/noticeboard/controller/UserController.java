package mini.noticeboard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.noticeboard.dto.UserDTO;
import mini.noticeboard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    // 생성자 주입
    private final UserService userService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";  // 화면만 띄워줌
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDTO userDTO) {
        System.out.println("userDTO = " + userDTO);
        userService.save(userDTO);  // db 처리가 필요함
        return "signin";
    }

    @GetMapping("/signin")
    public String signinForm() {
        return "signin";
    }

    @PostMapping("/signin")
    public String signin(@ModelAttribute UserDTO userDTO, HttpSession session) {
        UserDTO signinResult = userService.login(userDTO);
        if (signinResult != null) {
            // 로그인 성공
            session.setAttribute("loginName", signinResult.getUserName());
            session.setAttribute("loginId", signinResult.getId());
            return "main";
        } else {
            // 로그인 실패
            return "signin";
        }
    }

    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {
        String myName = (String) session.getAttribute("loginName");
        UserDTO userDTO = userService.updateForm(myName);
        model.addAttribute("updateUser", userDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute UserDTO userDTO) {
        userService.update(userDTO);
        return "main";
    }

    @GetMapping("/delete")
    public String showDeleteForm(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loginId");
        if (userId == null) {
            return "redirect:/signin";
        }
        model.addAttribute("userId", userId);
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, @RequestParam("pw") String pw, HttpSession session) {
        try {
            boolean isDelete = userService.deleteUser(id, pw);
            if (isDelete) {
                session.invalidate();  // 세션 무효화
                return "redirect:/";
            } else {
                return "redirect:/user/delete?error=password";
            }
        } catch (Exception e) {
            return "redirect:/user/delete?error=unknown";
        }
    }


}
