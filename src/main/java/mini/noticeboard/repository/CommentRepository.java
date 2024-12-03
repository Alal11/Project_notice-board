package mini.noticeboard.repository;

import mini.noticeboard.entity.BoardEntity;
import mini.noticeboard.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBoardEntityOrderByCommentIdAsc(BoardEntity boardEntity);

    int countByBoardEntity(BoardEntity boardEntity);
}
