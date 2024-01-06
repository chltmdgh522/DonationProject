package csh.football.board.domain.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardDto {

    String title;

    String content;

    boolean boardType;

    MultipartFile boardImage;
}
