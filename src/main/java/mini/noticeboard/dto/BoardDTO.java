package mini.noticeboard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor  // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {

    private Long boardId;
    private String userName;
    private String boardTitle;
    private String boardContents;
    private int boardViews;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;
}
