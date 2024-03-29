package csh.donation.member.domain.mypage;

import csh.donation.board.domain.repository.BoardRepository;
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
