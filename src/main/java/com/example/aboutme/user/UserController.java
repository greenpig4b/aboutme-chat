package com.example.aboutme.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;


    @GetMapping("/join")
    public String index() {
        return "oauth/join";
    }


//    //회원가입
//    @PostMapping("/join")
//    public String Join(UserRequest.JoinDTO reqDTO) {
//        System.out.println("reqDTO = " + reqDTO);
//    userService.joinByEmail(reqDTO);
//    return "redirect:/login";
//    }

    @GetMapping("/login")
    public String login() {
        return "oauth/login";
    }


    //    @PostMapping("/login")
//    public String login(String email, String password) {
//        System.out.println("email = " + email);
//        System.out.println("password = " + password);
//        return "redirect:/";
//    }
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.loginByName(reqDTO);
        System.out.println("sessionUser = " + sessionUser);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }


//    @GetMapping("/")
//    public String index() {
//        return "client/main";
//    }
//
//    @GetMapping("/client/findExpert/detail")
//    public String findExpertDetail() {
//        return "client/findExpert/detail";
//    }
//
//    @GetMapping("/client/findExpert")
//    public String findExpert() {
//        return "client/findExpert/main";
//    }
//
//    @GetMapping("/client/comm")
//    public String community() {
//        return "client/comm/comm-main";
//    }
//    @GetMapping("/client/findExpert/voucher")
//    public String findExpertVoucher() {
//        return "client/findExpert/voucher";
//    }

    // 👻👻👻공통👻👻👻
    // 메인페이지
    @GetMapping("/")
    public String expert() {
        return "client/main";
    }

    //TODO: 커뮤니티 페이지
    //커뮤니티 - 메인
    @GetMapping("/comm")
    public String community() {
        return "comm/comm-main";
    }


    // 🐯🐯🐯Client🐯🐯🐯
    //전문가 찾기 - 메인
    @GetMapping("/client/findExpert")
    public String findExpert() {
        return "client/findExpert/main";
    }

    //전문가 찾기 - 상세보기
    @GetMapping("/client/findExpert/detail")
    public String findExpertDetail() {
        return "client/findExpert/detail";
    }

    //클라이언트 - 마이페이지
    @GetMapping("/client/mypage")
    public String clientMypage() {
        return "client/mypage";
    }


    // 🩺🩺🩺expert🩺🩺🩺
}
