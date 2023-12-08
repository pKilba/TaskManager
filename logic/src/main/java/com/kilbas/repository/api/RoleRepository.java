package com.kilbas.repository.api;

import com.kilbas.model.Role;

import java.util.Optional;

public interface RoleRepository {

    Role create(Role entity);
    Optional<Role> findByName(String name);
}
