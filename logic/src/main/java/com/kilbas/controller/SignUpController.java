package com.kilbas.controller;

import com.kilbas.dto.UserDto;
import com.kilbas.exception.DuplicateEntityException;

import com.kilbas.model.User;
import com.kilbas.service.api.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@Tag(name="Регистрация", description="контроллер для регистрации")
@RestController
@RequestMapping("/signup")

public class SignUpController {

    private final UserService userService;


    public SignUpController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@RequestBody UserDto userDto)
            throws DuplicateEntityException {


        return  userService.register(userDto.toUser());

    }

}
