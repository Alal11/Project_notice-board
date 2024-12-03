package mini.noticeboard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long commentId;
    private String commentContents;
    private String commentWriter;
    private LocalDateTime createDate;
    private Long boardId;

}
