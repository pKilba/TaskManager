

import com.kilbas.controller.SignUpController;
import com.kilbas.dto.UserDto;
import com.kilbas.exception.DuplicateEntityException;
import com.kilbas.model.User;
import com.kilbas.service.api.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SignUpControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private SignUpController signUpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUp_Success() throws DuplicateEntityException {
        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setEmail("test@example.com");
        when(userService.register(any(User.class))).thenReturn(mockUser);

        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");

        User result = signUpController.signup(userDto);

        assertEquals(1, result.getId());
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void testSignUp_DuplicateEntityException() throws DuplicateEntityException {
        when(userService.register(any(User.class))).thenThrow(new DuplicateEntityException("User already exists"));

        UserDto userDto = new UserDto();
        userDto.setEmail("existing@example.com");

        try {
            signUpController.signup(userDto);
        } catch (DuplicateEntityException e) {
            assertEquals("User already exists", e.getMessage());
        }
    }
}
