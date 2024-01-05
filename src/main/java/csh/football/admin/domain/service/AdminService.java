package csh.football.admin.domain.service;

import csh.football.member.domain.member.Member;
import csh.football.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member adminLogin(String loginId, String password) {

        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if (member.get().getRole().equals("O")) {

            return memberRepository.findByLoginId(loginId)
                    .filter(a -> bCryptPasswordEncoder.matches(password, a.getPassword()))
                    .orElse(null);
        }
        return null;
    }

}
