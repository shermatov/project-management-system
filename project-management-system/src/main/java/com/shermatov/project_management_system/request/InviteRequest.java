package com.shermatov.project_management_system.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteRequest {
    private Long projectId;
    private String email;
}
