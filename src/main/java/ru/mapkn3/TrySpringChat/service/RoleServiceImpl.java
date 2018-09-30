package ru.mapkn3.TrySpringChat.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.TrySpringChat.dao.PrettyEntityDao;
import ru.mapkn3.TrySpringChat.model.Role;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class RoleServiceImpl implements RoleService {
    private final static Logger logger = Logger.getLogger(RoleServiceImpl.class);
    public static String A = "User";

    @Autowired
    private PrettyEntityDao roleDao;

    @Override
    @Transactional(readOnly = true)
    public Role getRole(Long id) {
        logger.debug("Getting role with id = " + id);
        return roleDao.findById(Role.class, id);
    }

    @Override
    public Role getRoleByName(String name) {
        List<Role> roles = roleDao.getAll(Role.class);
        Optional<Role> result = roles.stream().filter(role -> role.getName().equals(name)).findFirst();
        return result.orElse(null);
    }

    @Override
    public Long addNewRole(Role role) {
        Long id = (Long) roleDao.save(role);
        logger.debug("Id of new Role: " + id);
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        List<Role> roles = roleDao.getAll(Role.class);
        logger.debug("Get " + roles.size() + " roles:");
        for (Role role : roles) {
            logger.debug(role.toString());
        }
        return roles;
    }

    @Override
    public Role updateRole(Role role) {
        Role oldRole = roleDao.findById(Role.class, role.primaryKey());
        Role newRole = roleDao.update(role);
        logger.debug("Old role: " + oldRole.toString());
        logger.debug("New role: " + newRole.toString());
        return newRole;
    }

    @Override
    public void deleteRole(Role role) {
        logger.debug("Delete role: " + role.toString());
        roleDao.delete(role);
    }
}
