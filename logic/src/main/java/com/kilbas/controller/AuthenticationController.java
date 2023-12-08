package com.kilbas.controller;
import com.kilbas.dto.AuthenticationRequestDto;
import com.kilbas.model.User;
import com.kilbas.dto.AuthenticationResponseDto;
import com.kilbas.security.jwt.JwtTokenProvider;
import com.kilbas.service.api.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Tag(name="Логин", description="контроллер для авторизации")
@RestController
@RequestMapping("/login")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDto login(@RequestBody AuthenticationRequestDto requestDto) {
        String login = requestDto.getEmail();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, requestDto.getPassword()));
        User user = userService.findByUserEmail(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with email: " + login + " not found");
        }
        String token = jwtTokenProvider.createToken(login,user.getRoles());
        return new AuthenticationResponseDto(login, token);
    }
}