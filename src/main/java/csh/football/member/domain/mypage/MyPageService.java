package csh.football.member.domain.mypage;

import csh.football.board.repository.BoardRepository;
import csh.football.member.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final BoardRepository boardRepository;
    public void boardNameUpdate(String id, MyPageMember member){
        boardRepository.updateMemberName(id,member);

    }
}
