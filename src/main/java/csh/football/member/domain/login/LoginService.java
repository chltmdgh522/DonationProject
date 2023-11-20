package csh.football.member.domain.login;

import csh.football.member.domain.member.Member;
import csh.football.member.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member login(String loginId, String password) throws SQLException {

        return memberRepository.findByLoginId(loginId)
                .filter(m -> bCryptPasswordEncoder.matches(password, m.getPassword()))
                .orElse(null);
    }
}


