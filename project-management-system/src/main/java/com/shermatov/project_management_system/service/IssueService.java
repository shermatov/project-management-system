package com.shermatov.project_management_system.service;

import com.shermatov.project_management_system.model.Issue;
import com.shermatov.project_management_system.model.User;
import com.shermatov.project_management_system.request.IssueRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueService {
   Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userid) throws Exception;


    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;
}