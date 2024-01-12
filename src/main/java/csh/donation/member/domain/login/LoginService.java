package csh.donation.member.domain.login;

import csh.donation.member.domain.member.Member;
import csh.donation.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member login(String loginId, String password) {

        return memberRepository.findByLoginId(loginId)
                .filter(m -> bCryptPasswordEncoder.matches(password, m.getPassword()))
                .orElse(null);
    }

    public void point(Member loginMember) {
        Integer point = loginMember.getPoint() + 100;
        memberRepository.updatePoint(loginMember.getId(), point);
    }
}


