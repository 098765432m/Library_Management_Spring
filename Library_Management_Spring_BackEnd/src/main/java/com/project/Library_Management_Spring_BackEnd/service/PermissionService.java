package com.project.Library_Management_Spring_BackEnd.service;

import com.project.Library_Management_Spring_BackEnd.dto.request.PermissionRequest;
import com.project.Library_Management_Spring_BackEnd.dto.response.PermissionResponse;
import com.project.Library_Management_Spring_BackEnd.entity.Permission;
import com.project.Library_Management_Spring_BackEnd.mapper.PermissionMapper;
import com.project.Library_Management_Spring_BackEnd.repository.PermissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository, PermissionMapper permissionMapper){
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    //create
    @Transactional
    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.ToPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    //get All
    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    //delete
    @Transactional
    public void delete(String permissionName){
        permissionRepository.deleteById(permissionName);
    }
}
