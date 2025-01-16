package com.shermatov.project_management_system.service;

import com.shermatov.project_management_system.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    Comment creatComment(Long issueId, Long userId, String content) throws Exception;
    void deleteComment(Long commentId, Long userId) throws Exception;
    List<Comment> getCommentsByIssueId(Long issueId);
}
