package com.shermatov.project_management_system.controller;

import com.shermatov.project_management_system.model.Chat;
import com.shermatov.project_management_system.model.Message;
import com.shermatov.project_management_system.model.User;
import com.shermatov.project_management_system.request.CreateMessageRequest;
import com.shermatov.project_management_system.service.MessageService;
import com.shermatov.project_management_system.service.ProjectService;
import com.shermatov.project_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestBody CreateMessageRequest request)
            throws Exception{
        User user = userService.findUserByUserId(request.getSenderId());
        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();

        if(chat == null) throw new Exception("User not found");
        Message sendMessage = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(), request.getContent());
        return ResponseEntity.ok(sendMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByProjectId(
            @PathVariable Long projectId) throws Exception{
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}
