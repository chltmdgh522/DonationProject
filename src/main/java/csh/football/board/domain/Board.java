package csh.football.board.domain;

import lombok.Data;

@Data
public class Board {

    Long id;

    String boardId;

    String title;

    String content;

    String memberName;

    String memberId;

    int viewCount;

    String date;

}
