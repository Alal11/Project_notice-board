package mini.noticeboard.repository;

import mini.noticeboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findAllByOrderByBoardIdDesc();

    // 제목으로 검색
    List<BoardEntity> findByBoardTitleContaining(String keyword);

    // 내용으로 검색
    List<BoardEntity> findByBoardContentsContaining(String keyword);

    // 제목 또는 내용으로 검색
    List<BoardEntity> findByBoardTitleContainingOrBoardContentsContaining(String title, String content);
}
