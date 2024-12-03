package mini.noticeboard.repository;

import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.BoardDTO;
import mini.noticeboard.entity.BoardEntity;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sql;

    // 게시판 목록 조회
    public List<BoardDTO> getList(){
        return sql.selectList("Board.getList");  // board-mapper 파일
    }

    // 게시판 추가
    public void save(BoardEntity boardEntity){
        sql.insert("Board.save", boardEntity);
    }
}
