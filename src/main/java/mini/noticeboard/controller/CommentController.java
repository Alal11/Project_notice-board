package mini.noticeboard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.CommentDTO;
import mini.noticeboard.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save/{boardId}")
    public String save(@PathVariable("boardId") Long boardId,
                       @ModelAttribute CommentDTO commentDTO, HttpSession session) {
        String loginName = (String) session.getAttribute("loginName");
        commentDTO.setCommentWriter(loginName);
        commentDTO.setBoardId(boardId);
        commentService.save(commentDTO);

        return "redirect:/board/" + boardId;
    }
}
