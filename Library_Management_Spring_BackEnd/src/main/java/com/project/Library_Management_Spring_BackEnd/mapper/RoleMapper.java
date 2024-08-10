package com.project.Library_Management_Spring_BackEnd.mapper;

import com.project.Library_Management_Spring_BackEnd.dto.request.RoleRequest;
import com.project.Library_Management_Spring_BackEnd.dto.response.RoleResponse;
import com.project.Library_Management_Spring_BackEnd.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);
    RoleResponse toRoleResponse(Role role);
}
