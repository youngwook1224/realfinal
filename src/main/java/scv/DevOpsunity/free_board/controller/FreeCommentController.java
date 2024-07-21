package scv.DevOpsunity.free_board.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scv.DevOpsunity.free_board.dto.FreeCommentDTO;
import scv.DevOpsunity.free_board.service.FreeCommentService;
import scv.DevOpsunity.member.dto.MemberDTO;

import java.util.List;

@RestController
@RequestMapping("/freeComment")
public class FreeCommentController {

    @Autowired
    private FreeCommentService freeCommentService;

    @GetMapping("/list/{freeArticleNo}")
    public ResponseEntity<List<FreeCommentDTO>> listComments(@PathVariable("freeArticleNo") int freeArticleNo) {
        try {
            List<FreeCommentDTO> commentList = freeCommentService.listComments(freeArticleNo);
            return new ResponseEntity<>(commentList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addComment")
    public ResponseEntity<String> addComment(@RequestParam("freeArticleNo") int freeArticleNo,
                                             @RequestParam("commentContent") String commentContent,
                                             HttpSession session) {
        try {
            MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
            if (memberDTO == null) {
                return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
            }

            FreeCommentDTO freeCommentDTO = new FreeCommentDTO();
            freeCommentDTO.setFreeArticleNo(freeArticleNo);
            freeCommentDTO.setCommentContent(commentContent);
            freeCommentDTO.setId(memberDTO.getId());

            freeCommentService.addComment(freeCommentDTO);
            return new ResponseEntity<>("Comment added successfully", HttpStatus.OK);
        } catch (Exception e) {
            // 로깅 추가
            return new ResponseEntity<>("Error adding comment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/modify")
    public ResponseEntity<String> modifyComment(@RequestBody FreeCommentDTO freeCommentDTO) {
        try {
            freeCommentService.modifyComment(freeCommentDTO);
            return new ResponseEntity<>("Comment modified successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove/{commentNo}")
    public ResponseEntity<String> removeComment(@PathVariable("commentNo") int commentNo) {
        try {
            freeCommentService.removeComment(commentNo);
            return new ResponseEntity<>("Comment removed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}