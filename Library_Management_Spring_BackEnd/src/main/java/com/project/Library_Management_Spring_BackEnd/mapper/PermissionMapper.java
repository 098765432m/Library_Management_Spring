package com.project.Library_Management_Spring_BackEnd.mapper;

import com.project.Library_Management_Spring_BackEnd.dto.request.PermissionRequest;
import com.project.Library_Management_Spring_BackEnd.dto.response.PermissionResponse;
import com.project.Library_Management_Spring_BackEnd.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission ToPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);
}
