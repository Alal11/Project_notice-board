package mini.noticeboard.service;

import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.BoardDTO;
import mini.noticeboard.entity.BoardEntity;
import mini.noticeboard.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시판 목록 조회
    public List<BoardDTO> getList() {
        return boardRepository.getList();

    }

    // 게시판 작성 (DB 저장)
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
