package com.shermatov.project_management_system.service;

import com.shermatov.project_management_system.model.Invitation;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface InvitationService {

    public void sendInvitation(String email, Long projectId) throws MessagingException;
    public Invitation acceptInvitation(String token, Long userId) throws Exception;

    public String getTokenByUserName(String username);
    void deleteToken(String token);

}
