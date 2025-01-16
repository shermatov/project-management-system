package com.shermatov.project_management_system.service;

import com.shermatov.project_management_system.model.Chat;
import org.springframework.stereotype.Service;

@Service
public interface CharService {

    Chat createChat(Chat chat);
}
