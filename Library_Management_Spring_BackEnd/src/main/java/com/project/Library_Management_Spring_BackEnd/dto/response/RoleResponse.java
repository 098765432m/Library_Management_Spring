package com.project.Library_Management_Spring_BackEnd.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {

    private String name;
    private String description;
    private Set<PermissionResponse> permissions;
}
