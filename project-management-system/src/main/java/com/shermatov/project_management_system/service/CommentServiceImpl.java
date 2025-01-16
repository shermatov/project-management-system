package com.shermatov.project_management_system.service;

import com.shermatov.project_management_system.model.Comment;
import com.shermatov.project_management_system.model.Issue;
import com.shermatov.project_management_system.model.User;
import com.shermatov.project_management_system.repository.CommentRepository;
import com.shermatov.project_management_system.repository.IssueRepository;
import com.shermatov.project_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Comment creatComment(Long issueId, Long userId, String content) throws Exception {

        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (issueOptional.isEmpty()) {
            throw new Exception("Issue with id " + issueId + " not found");
        }

        if(userOptional.isEmpty()){
            throw new Exception("User with id " + userId + " not found");
        }

        Issue issue = issueOptional.get();
        User user = userOptional.get();
        Comment comment = new Comment();
        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedAtTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepository.save(comment);

        issue.getComments().add(savedComment);

        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (commentOptional.isEmpty()) {
            throw new Exception("Issue with id " + commentId + " not found");
        }

        if(userOptional.isEmpty()){
            throw new Exception("User with id " + userId + " not found");
        }

        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if(comment.getUser().equals(user)){
            commentRepository.delete(comment);
        }else {
            throw new Exception("Comment with id " + commentId + " is not owned by user " + user.getId());
        }


    }

    @Override
    public List<Comment> getCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }
}
