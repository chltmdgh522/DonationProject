package csh.football.board.service;



import csh.football.board.domain.Board;
import csh.football.board.repository.BoardRepository;
import csh.football.member.domain.member.Member;
import csh.football.member.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //마이페이지에서 해당유저 게시물 보여주기
    public List<Board> userCheckService(String memberId) {
        List<Board> all = boardRepository.findAll();
        List<Board> boards = new ArrayList<>();
        for (Board board : all) {
            if (board.getMemberId().equals(memberId)) {
                boards.add(board);
            }
        }
        return boards;
    }

    public Member findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId)
                .orElse(null);
    }

    // 유저의 계정 게시물마다 boardId증가
    public int addIdService(String memberId) {
        List<Board> all = boardRepository.findAll();
        int cnt = 1;
        if (all != null) {
            for (Board fboard : all) {
                if (fboard.getMemberId().equals(memberId)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public void boardSaveService(Member member, Board board, String memberId) {
        board.setMemberName(member.getName());
        board.setMemberId(member.getId());
        int n = addIdService(memberId);
        board.setBoardId(String.valueOf(n));
        boardRepository.save(board);
    }

    //홈에서 게시판 클릭하면 게시판 보여주기 및 편집 버튼
    public Board boardCheckService(String memberId, String boardId) {


        return boardRepository.findByMemberIdAndBoardId(memberId, boardId)
                .orElse(null);
    }

    //게시물 조회수 증가
    public void addViewCount(String memberId, String boardId) {
        Optional<Board> board = boardRepository.findByMemberIdAndBoardId(memberId, boardId);
        log.info("d={}",board.get().getViewCount());
        log.info("d={}",board.get().getViewCount()+1);
        log.info("d={}",board.get().getId());
        int count=board.get().getViewCount()+1;
        boardRepository.updateViewCount(board.get().getId(),count);
    }
}
