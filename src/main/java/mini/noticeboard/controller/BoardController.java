package mini.noticeboard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.noticeboard.dto.BoardDTO;
import mini.noticeboard.service.BoardService;
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

    // 게시판 목록 조회
    @GetMapping("/")
    public String getList(Model model){
        // DB에서 전체 게시글 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.getList();
        model.addAttribute("boardDTOList", boardDTOList);
        return "boardList";
    }

    // 게시판 작성 화면 호출
    @GetMapping("/writeForm")
    public String writeForm() {
        System.out.println("게시판 작성 화면 컨트롤러");
        return "writeForm";
    }

    // 게시판 작성 (DB 저장)
    @PostMapping("/writeForm")
    public String save(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
        String loginName = (String) session.getAttribute("loginName");
        boardDTO.setUserName(loginName);
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/board/";
    }


}
