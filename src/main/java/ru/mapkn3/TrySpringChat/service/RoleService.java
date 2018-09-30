package ru.mapkn3.TrySpringChat.service;

import ru.mapkn3.TrySpringChat.model.Role;

import java.util.List;

public interface RoleService {
    Role getRole(Long id);

    Role getRoleByName(String name);

    Long addNewRole(Role role);

    List<Role> getAllRoles();

    Role updateRole(Role role);

    void deleteRole(Role role);
}
