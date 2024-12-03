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

    public BoardDTO toDTO() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardId(this.boardId);
        boardDTO.setUserName(this.userName);
        boardDTO.setBoardTitle(this.boardTitle);
        boardDTO.setBoardContents(this.boardContents);
        boardDTO.setBoardViews(this.boardViews);
        boardDTO.setCreateDate(this.getCreateDate());
        boardDTO.setModifiedDate(this.getModifiedDate());
        return boardDTO;
    }

}
