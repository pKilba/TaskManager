package com.kilbas.repository.impl;

import com.kilbas.model.User;
import com.kilbas.repository.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager, User.class);
    }

}
