package mini.noticeboard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.CommentDTO;
import mini.noticeboard.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // 댓글 삭제
    @GetMapping("/delete/{commentId}")
    public String delete(@PathVariable("commentId") Long commentId){
        Long boardId = commentService.getBoardId(commentId);
        commentService.delete(commentId);
        return "redirect:/board/" + boardId;
    }
}
