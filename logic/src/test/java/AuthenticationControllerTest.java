import com.kilbas.controller.AuthenticationController;
import com.kilbas.dto.AuthenticationRequestDto;
import com.kilbas.dto.AuthenticationResponseDto;
import com.kilbas.model.Role;
import com.kilbas.model.User;
import com.kilbas.security.jwt.JwtTokenProvider;
import com.kilbas.service.api.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto("test@example.com", "password");
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
       // user.setRoles(Collections.singleton(Role.));

        when(userService.findByUserEmail(any())).thenReturn(user);
        when(jwtTokenProvider.createToken(any(), any())).thenReturn("mocked_token");

        AuthenticationResponseDto expectedResponse = new AuthenticationResponseDto("test@example.com", "mocked_token");
        AuthenticationResponseDto actualResponse = authenticationController.login(requestDto);

        assertEquals(expectedResponse.getLogin(), actualResponse.getLogin());
        assertEquals(expectedResponse.getToken(), actualResponse.getToken());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
    @Test
    void testLogin_UserNotFound() {
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto("notfound@example.com", "password");

        when(userService.findByUserEmail(any())).thenReturn(null);

        try {
            authenticationController.login(requestDto);
        } catch (UsernameNotFoundException e) {
            assertEquals("User with email: notfound@example.com not found", e.getMessage());
        }

    }

}
