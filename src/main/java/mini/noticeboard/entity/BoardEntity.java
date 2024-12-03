package mini.noticeboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mini.noticeboard.dto.BoardDTO;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private String userName;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardViews;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setUserName(boardDTO.getUserName());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardViews(0);
        return boardEntity;
    }

}
