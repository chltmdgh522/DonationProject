package csh.football.member.web.password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PasswordController {

    @GetMapping("/change-password")
    public String changePassword(){
        return "changepassword/change-password";
    }
}
