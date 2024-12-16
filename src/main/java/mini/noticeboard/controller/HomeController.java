package mini.noticeboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.noticeboard.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import mini.noticeboard.dto.BoardDTO;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final BoardService boardService;  // BoardService 주입

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/main")
    public String main(Model model,
                       @PageableDefault(page = 0, size = 5, sort = "boardId", direction = Sort.Direction.DESC)
                       Pageable pageable) {
        // 게시글 목록 로직 추가
        Page<BoardDTO> boardDTOList = boardService.getList(pageable);
        model.addAttribute("boardDTOList", boardDTOList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", boardDTOList.hasNext());
        model.addAttribute("hasPrev", boardDTOList.hasPrevious());
        return "main";
    }
}