package mini.noticeboard.service;

import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.BoardDTO;
import mini.noticeboard.entity.BoardEntity;
import mini.noticeboard.repository.BoardRepository;
import mini.noticeboard.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    // 게시판 목록 조회
    public Page<BoardDTO> getList(Pageable pageable) {
        Page<BoardEntity> boardEntities = boardRepository.findAllByOrderByBoardIdDesc(pageable);
        return boardEntities.map(entity -> {
            BoardDTO dto = new BoardDTO();
            dto.setBoardId(entity.getBoardId());
            dto.setUserName(entity.getUserName());
            dto.setBoardTitle(entity.getBoardTitle());
            dto.setBoardContents(entity.getBoardContents());
            dto.setBoardViews(entity.getBoardViews());
            dto.setCommentCount(commentRepository.countByBoardEntity(entity));
            dto.setCreateDate(entity.getCreateDate());
            dto.setModifiedDate(entity.getModifiedDate());
            return dto;
        });
    }

    // 게시판 작성 (DB 저장)
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }


    // 게시글 상세 조회
    public BoardDTO findById(Long id){
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            return toBoardDTO(boardEntity);
        }else{
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }
    }

    // 조회수 증가
    public void updateViews(Long id){
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        boardEntity.setBoardViews(boardEntity.getBoardViews() + 1);
        boardRepository.save(boardEntity);
    }

    // 게시글 수정
    public void update(BoardDTO boardDTO){
        BoardEntity boardEntity = boardRepository.findById(boardDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardRepository.save(boardEntity);
    }

    // 게시글 삭제
    public void delete(Long id){
        boardRepository.deleteById(id);
    }

    // 게시글 검색 기능
    public List<BoardDTO> search(String searchType, String keyword){
        List<BoardEntity> boardEntityList;

        switch (searchType){
            case "title":
                boardEntityList = boardRepository.findByBoardTitleContaining(keyword);
                break;
            case "content":
                boardEntityList = boardRepository.findByBoardContentsContaining(keyword);
                break;
            case "titleContent":
                boardEntityList = boardRepository.findByBoardTitleContainingOrBoardContentsContaining(keyword, keyword);
                break;
            default:
                throw new IllegalArgumentException("잘못된 검색 유형입니다.");
        }

        return boardEntityList.stream()
                .map(entity -> {
                    BoardDTO dto = new BoardDTO();
                    dto.setBoardId(entity.getBoardId());
                    dto.setUserName(entity.getUserName());
                    dto.setBoardTitle(entity.getBoardTitle());
                    dto.setBoardContents(entity.getBoardContents());
                    dto.setBoardViews(entity.getBoardViews());
                    dto.setCommentCount(commentRepository.countByBoardEntity(entity));
                    dto.setCreateDate(entity.getCreateDate());
                    dto.setModifiedDate(entity.getModifiedDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }



    // Entity를 DTO로 변환하는 메소드
    private BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardId(boardEntity.getBoardId());
        boardDTO.setUserName(boardEntity.getUserName());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardViews(boardEntity.getBoardViews());
        boardDTO.setCommentCount(commentRepository.countByBoardEntity(boardEntity));
        boardDTO.setCreateDate(boardEntity.getCreateDate());
        boardDTO.setModifiedDate(boardEntity.getModifiedDate());
        return boardDTO;
    }
}
