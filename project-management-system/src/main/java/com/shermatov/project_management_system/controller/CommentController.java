package com.shermatov.project_management_system.controller;

import com.shermatov.project_management_system.model.Comment;
import com.shermatov.project_management_system.model.User;
import com.shermatov.project_management_system.request.CreateCommentRequest;
import com.shermatov.project_management_system.response.MessageResponse;
import com.shermatov.project_management_system.service.CommentService;
import com.shermatov.project_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Comment>  createComment(
            @RequestBody CreateCommentRequest req,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        Comment createdComment = commentService.creatComment(
                req.getIssueId(),
                user.getId(),
                req.getContent());

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());

        MessageResponse messageResponse = new MessageResponse("Comment deleted successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity <List<Comment>> getCommentByIssueId(@PathVariable Long issueId) {
        List<Comment> comments = commentService.getCommentsByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
