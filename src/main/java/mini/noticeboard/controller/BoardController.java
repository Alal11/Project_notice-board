package mini.noticeboard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.noticeboard.dto.BoardDTO;
import mini.noticeboard.dto.CommentDTO;
import mini.noticeboard.service.BoardService;
import mini.noticeboard.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    // 게시판 목록 조회
    @GetMapping("/")
    public String getList(Model model,
                          @PageableDefault(page = 0, size = 5, sort = "boardId", direction = Sort.Direction.DESC)
                          Pageable pageable) {
        // DB에서 전체 게시글 가져와서 list.html에 보여준다.
        Page<BoardDTO> boardDTOList = boardService.getList(pageable);
        model.addAttribute("boardDTOList", boardDTOList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", boardDTOList.hasNext());
        model.addAttribute("hasPrev", boardDTOList.hasPrevious());
        return "board/boardList";
    }

    // 게시판 작성 화면 호출
    @GetMapping("/writeForm")
    public String writeForm() {
        log.info("게시판 작성 화면 컨트롤러");
        return "board/writeForm";
    }

    // 게시판 작성 (DB 저장)
    @PostMapping("/writeForm")
    public String save(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
        String loginName = (String) session.getAttribute("loginName");
        boardDTO.setUserName(loginName);
        log.info("boardDTO = {}", boardDTO);
        boardService.save(boardDTO);
        return "redirect:/board/";
    }


    // 게시글 상세 보기 화면 호출
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id,
                         @PageableDefault(page = 0, size = 5, sort = "commentId", direction = Sort.Direction.ASC) Pageable pageable,
                         Model model) {
        BoardDTO boardDTO = boardService.findById(id);

        // 조회수 증가
        boardService.updateViews(id);

        // 댓글 목록 가져오기
        Page<CommentDTO> commentDTOList = commentService.findAll(id, pageable);
        model.addAttribute("board", boardDTO);
        model.addAttribute("commentDTOList", commentDTOList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", commentDTOList.hasNext());
        model.addAttribute("hasPrev", commentDTOList.hasPrevious());

        return "board/detail";
    }

    // 게시글 수정 폼 호출
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "board/update";
    }

    // 게시글 수정 (DB 저장)
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO) {
        boardService.update(boardDTO);
        return "redirect:/board/" + boardDTO.getBoardId();
    }

    // 게시글 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }

    // 키워드 검색
    @GetMapping("/search")
    public String search(@RequestParam("searchType") String searchType,
                         @RequestParam("keyword") String keyword, Model model) {
        List<BoardDTO> searchList = boardService.search(searchType, keyword);
        model.addAttribute("boardDTOList", searchList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        return "board/boardList";
    }

}
