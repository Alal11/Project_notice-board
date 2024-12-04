package mini.noticeboard.service;

import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.CommentDTO;
import mini.noticeboard.entity.BoardEntity;
import mini.noticeboard.entity.CommentEntity;
import mini.noticeboard.repository.BoardRepository;
import mini.noticeboard.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<CommentDTO> findAll(Long boardId, Pageable pageable){
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));

        Page<CommentEntity> commentEntities = commentRepository.findAllByBoardEntityOrderByCommentIdAsc(boardEntity, pageable);

        return commentEntities.map(entity -> {
            CommentDTO dto = new CommentDTO();
            dto.setCommentId(entity.getCommentId());
            dto.setCommentContents(entity.getCommentContents());
            dto.setCommentWriter(entity.getCommentWriter());
            dto.setCreateDate(entity.getCreateDate());
            dto.setBoardId(entity.getBoardEntity().getBoardId());
            return dto;
        });
    }

    // 댓글이 속한 게시글의 id 가져오기
    public Long getBoardId(Long commentId){
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        return commentEntity.getBoardEntity().getBoardId();
    }

    // 댓글 삭제
    public void delete(Long commentId){
        commentRepository.deleteById(commentId);
    }
}
