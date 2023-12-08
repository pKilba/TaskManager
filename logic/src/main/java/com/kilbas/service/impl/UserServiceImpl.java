package com.kilbas.service.impl;

import com.kilbas.exception.DuplicateEntityException;
import com.kilbas.exception.IncorrectDataException;
import com.kilbas.exception.NotFoundEntityException;
import com.kilbas.exception.NotValidEntityException;
import com.kilbas.model.Role;
import com.kilbas.model.Status;
import com.kilbas.model.User;
import com.kilbas.repository.api.RoleRepository;
import com.kilbas.repository.api.UserRepository;
import com.kilbas.security.jwt.JwtTokenProvider;
import com.kilbas.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userDao;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userDao, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userDao = userDao;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public List<User> findAll(int page, int size) {

        return userDao.findAllWithPagination(page, size);
    }

    @Override
    @Transactional
    public User findByUserEmail(String mail) {
        return userDao.findByField("email", mail)
                .orElseThrow(() -> new NotFoundEntityException("not "));
    }

    @Transactional
    public User register(User userDto) {
        User user = userDto;
        if (user.getEmail() == null || user.getName() == null) {
            throw new NotValidEntityException("data.incorrect");
        }
        if (userDao.findByField("email", user.getEmail()).isPresent()) {
            throw new DuplicateEntityException("user.exist");
        }
        Optional<Role> roleUserOptional = roleRepository.findByName("ROLE_USER");
        if (roleUserOptional.isEmpty()) {
            Role roleUser = new Role("ROLE_USER");
            roleRepository.create(roleUser);
        }
        Role roleUser = roleRepository.findByName("ROLE_USER").get();
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRoles(userRoles);
        return userDao.create(user);
    }

    public void checkAccess(HttpServletRequest httpServletRequest, long userId) {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        String login = jwtTokenProvider.getUsername(token);
        List<String> roles = jwtTokenProvider.getRoles(token);
        User userDto = userDao.findByField("email", login).get(); //  userService.findByUsername(login);
        if ((userDto.getId() != userId) && !roles.contains("ROLE_ADMIN")) {
            throw new AuthorizationServiceException("Exception no access");
        }
    }

    @Transactional
    public User findById(long id) throws NotFoundEntityException {
        return userDao.findById(id).orElseThrow(() -> new NotFoundEntityException("Not found user"));
    }

}
