package csh.football.member.domain.service;

import csh.football.member.domain.member.Member;
import csh.football.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findByMemberId(String id){
        return memberRepository.findByMemberId(id).orElse(null);
    }

    public void updatePassword(String id, String newPassword){
        memberRepository.updatePassword(id, newPassword);
    }
}
