package com.tech.userapi.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;
    private Date createdDate;
    private Date modifiedDate;
    private Date lastLogin;
    private String token;
    private Boolean isActive;
}
