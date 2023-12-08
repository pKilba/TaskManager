package com.kilbas.repository.impl;

import com.kilbas.model.Role;
import com.kilbas.repository.api.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl extends AbstractRepository<Role> implements RoleRepository {

    @Autowired
    public RoleRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Role.class);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return  findByField("name", name);
    }
}