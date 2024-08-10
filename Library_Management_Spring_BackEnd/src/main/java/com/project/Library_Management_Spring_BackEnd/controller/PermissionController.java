package com.project.Library_Management_Spring_BackEnd.controller;

import com.project.Library_Management_Spring_BackEnd.dto.request.ApiResponse;
import com.project.Library_Management_Spring_BackEnd.dto.request.PermissionRequest;
import com.project.Library_Management_Spring_BackEnd.dto.response.PermissionResponse;
import com.project.Library_Management_Spring_BackEnd.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService){
        this.permissionService = permissionService;
    }

    @GetMapping("/")
    public ApiResponse<List<PermissionResponse>> getAll(){
        List<PermissionResponse> permissions = permissionService.getAll();

        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissions)
                .build();
    }

    @PostMapping("/")
    public ApiResponse<PermissionResponse> createOne(@RequestBody PermissionRequest request){
        PermissionResponse response = permissionService.create(request);

        return ApiResponse.<PermissionResponse>builder()
                .result(response)
                .build();
    }

    @DeleteMapping("/{permissionName}")
    public ApiResponse<String> deleteOne(@PathVariable String permissionName){
        permissionService.delete(permissionName);

        return ApiResponse.<String>builder()
                .message("Xóa permission: " + permissionName + " thành công!")
                .build();
    }

}
