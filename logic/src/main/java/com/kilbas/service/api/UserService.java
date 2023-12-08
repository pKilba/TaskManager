package com.kilbas.service.api;

import com.kilbas.exception.NotFoundEntityException;
import com.kilbas.model.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    User findByUserEmail(String username);

    User register(User user);

    void checkAccess(HttpServletRequest httpServletRequest, long userId);

    /**
     * @param page page for pagination
     * @param size size for pagination
     * @return list users
     */
    List<User> findAll(int page, int size);

    /**
     * @param id id user
     * @return user
     * @throws NotFoundEntityException
     */
    User findById(long id) throws NotFoundEntityException;
}
