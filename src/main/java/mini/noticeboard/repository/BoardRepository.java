package mini.noticeboard.repository;

import mini.noticeboard.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // 제목으로 검색
    Page<BoardEntity> findByBoardTitleContaining(String keyword, Pageable pageable);

    // 내용으로 검색
    Page<BoardEntity> findByBoardContentsContaining(String keyword, Pageable pageable);

    // 제목 또는 내용으로 검색
    Page<BoardEntity> findByBoardTitleContainingOrBoardContentsContaining(String title, String content, Pageable pageable);

    // 게시글 내림차순 + 페이징 처리
    Page<BoardEntity> findAllByOrderByBoardIdDesc(Pageable pageable);
}
