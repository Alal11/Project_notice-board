package mini.noticeboard.service;

import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.CommentDTO;
import mini.noticeboard.entity.BoardEntity;
import mini.noticeboard.entity.CommentEntity;
import mini.noticeboard.repository.BoardRepository;
import mini.noticeboard.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public void save(CommentDTO commentDTO){
        BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);

        commentRepository.save(commentEntity);
    }

    public List<CommentDTO> findAll(Long boardId){
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByCommentIdAsc(boardEntity);

        return commentEntityList.stream()
                .map(entity -> new CommentDTO(
                        entity.getCommentId(),
                        entity.getCommentContents(),
                        entity.getCommentWriter(),
                        entity.getCreateDate(),
                        entity.getBoardEntity().getBoardId()))
                .collect(Collectors.toList());
    }
}
