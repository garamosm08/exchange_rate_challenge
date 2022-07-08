package com.bcp.challenge.exchangerate.service;

import com.bcp.challenge.exchangerate.model.RoleModel;

import java.util.List;

public interface RoleService {

    RoleModel createRole(RoleModel roleModel);
    List<RoleModel> getAllRoles();
    RoleModel getRoleById(Long roleId);
    void deleteRoleById(Long roleId);
}
