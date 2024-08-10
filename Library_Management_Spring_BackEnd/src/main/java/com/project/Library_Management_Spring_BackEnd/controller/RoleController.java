package com.project.Library_Management_Spring_BackEnd.controller;

import com.project.Library_Management_Spring_BackEnd.dto.request.ApiResponse;
import com.project.Library_Management_Spring_BackEnd.dto.request.RoleRequest;
import com.project.Library_Management_Spring_BackEnd.dto.response.RoleResponse;
import com.project.Library_Management_Spring_BackEnd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping("/")
    public ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @PostMapping("/")
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @DeleteMapping("/{roleName}")
    public ApiResponse<String> delete(@PathVariable String roleName){
        roleService.delete(roleName);

        return ApiResponse.<String>builder()
                .message("Xóa role: " + roleName + " thành công!")
                .build();
    }
}
