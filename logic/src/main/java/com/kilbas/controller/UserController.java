package com.kilbas.controller;

import com.kilbas.exception.DuplicateEntityException;
import com.kilbas.exception.NotFoundEntityException;
import com.kilbas.model.User;
import com.kilbas.service.api.UserService;
import com.kilbas.validator.impl.RequestParametersValidator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.List;

import static com.kilbas.util.RequestParammetr.DEFAULT_PAGE;
import static com.kilbas.util.RequestParammetr.DEFAULT_SIZE;
import static com.kilbas.util.RequestParammetr.PAGE;
import static com.kilbas.util.RequestParammetr.SIZE;


@Tag(name="Пользователь", description="контроллер для взаимодействие с пользователями")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RequestParametersValidator requestParametersValidator;

    @Autowired
    public UserController(UserService userService,
                          RequestParametersValidator requestParametersValidator) {
        this.userService = userService;
        this.requestParametersValidator = requestParametersValidator;
    }



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "JWT")
    public List<User> findAll(
            @RequestParam(value = PAGE, required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = SIZE, required = false, defaultValue = DEFAULT_SIZE) int size)
            throws InvalidParameterException {
        requestParametersValidator.paginationParamValid(page, size);
        return userService.findAll(page, size);
    }

    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable long id) throws NotFoundEntityException {
        requestParametersValidator.idParamValid(id);
        return userService.findById(id);
    }
}
