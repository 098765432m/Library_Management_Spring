package com.project.Library_Management_Spring_BackEnd.service;

import com.project.Library_Management_Spring_BackEnd.dto.request.RoleRequest;
import com.project.Library_Management_Spring_BackEnd.dto.response.RoleResponse;
import com.project.Library_Management_Spring_BackEnd.entity.Role;
import com.project.Library_Management_Spring_BackEnd.mapper.RoleMapper;
import com.project.Library_Management_Spring_BackEnd.repository.PermissionRepository;
import com.project.Library_Management_Spring_BackEnd.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper,
                       PermissionRepository permissionRepository){
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.permissionRepository = permissionRepository;
    }

    //get All
    public List<RoleResponse> getAll(){
        var roles = roleRepository.findAll();

        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    //Create
    @Transactional
    public RoleResponse create(RoleRequest request){
        RoleResponse response = new RoleResponse();

        Role newRole = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        newRole.setPermissions(new HashSet<>(permissions));

        response = roleMapper.toRoleResponse(roleRepository.save(newRole));

        return response;
    }

    //Delete
    @Transactional
    public void delete(String roleName){
        roleRepository.deleteById(roleName);
    }
}
