package com.shermatov.project_management_system.repository;

import com.shermatov.project_management_system.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}
