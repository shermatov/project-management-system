package com.shermatov.project_management_system.service;

import com.shermatov.project_management_system.model.Chat;
import com.shermatov.project_management_system.model.Message;
import com.shermatov.project_management_system.model.User;
import com.shermatov.project_management_system.repository.MessageRepository;
import com.shermatov.project_management_system.repository.ProjectRepository;
import com.shermatov.project_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectService projectService;

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {

        Chat chat = projectService.getChatByProjectId(projectId);
        return messageRepository.findByChatIdOrderByCreatedAtTimeAsc(chat.getId());

    }

    @Override
    public Message sendMessage(Long senderId, Long projectId, String context) throws Exception {

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + senderId));

        Chat chat= projectService.getProjectById(projectId).getChat();

        Message message = new Message();
        message.setContent(context);
        message.setSender(sender);
        message.setCreatedAtTime(LocalDateTime.now());
        message.setChat(chat);

        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        return savedMessage;
    }
}
