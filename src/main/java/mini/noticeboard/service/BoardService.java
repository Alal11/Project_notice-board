package mini.noticeboard.service;

import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.BoardDTO;
import mini.noticeboard.entity.BoardEntity;
import mini.noticeboard.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시판 목록 조회
    public List<BoardDTO> getList(){
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        return boardEntityList.stream()
                .map(entity -> {
                    BoardDTO dto = new BoardDTO();
                    dto.setBoardId(entity.getBoardId());
                    dto.setUserName(entity.getUserName());
                    dto.setBoardTitle(entity.getBoardTitle());
                    dto.setBoardContents(entity.getBoardContents());
                    dto.setBoardViews(entity.getBoardViews());
                    dto.setCreateDate(entity.getCreateDate());
                    dto.setModifiedDate(entity.getModifiedDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // 게시판 작성 (DB 저장)
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
