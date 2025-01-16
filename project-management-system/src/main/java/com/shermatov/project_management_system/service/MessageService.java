package com.shermatov.project_management_system.service;

import com.shermatov.project_management_system.model.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long projectId, String context) throws Exception;
    List<Message> getMessagesByProjectId(Long projectId) throws Exception;
}
