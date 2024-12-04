package mini.noticeboard.repository;

import mini.noticeboard.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // 제목으로 검색
    List<BoardEntity> findByBoardTitleContaining(String keyword);

    // 내용으로 검색
    List<BoardEntity> findByBoardContentsContaining(String keyword);

    // 제목 또는 내용으로 검색
    List<BoardEntity> findByBoardTitleContainingOrBoardContentsContaining(String title, String content);

    // 게시글 내림차순 + 페이징 처리
    Page<BoardEntity> findAllByOrderByBoardIdDesc(Pageable pageable);
}
