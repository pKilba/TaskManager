import com.kilbas.TaskManagementSystem;
import com.kilbas.exception.DuplicateEntityException;
import com.kilbas.model.Role;
import com.kilbas.model.Status;
import com.kilbas.model.User;
import com.kilbas.repository.api.RoleRepository;
import com.kilbas.repository.api.UserRepository;
import com.kilbas.security.jwt.JwtTokenProvider;
import com.kilbas.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TaskManagementSystem.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testRegisterUser_Success() {

        User user = new User();
        user.setEmail("test@example.com");
        user.setName("Test User");
        user.setPassword("testPassword");
        when(userRepository.findByField(eq("email"), anyString())).thenReturn(Optional.empty());
        when(roleRepository.findByName(eq("ROLE_USER"))).thenReturn(Optional.of(new Role("ROLE_USER")));
        when(userRepository.create(any(User.class))).thenReturn(user);
        User registeredUser = userService.register(user);
        assertNotNull(registeredUser);
        assertEquals("test@example.com", registeredUser.getEmail());
        assertEquals("Test User", registeredUser.getName());
        assertEquals(Status.ACTIVE, registeredUser.getStatus());
        assertEquals(1, registeredUser.getRoles().size());
        assertEquals("ROLE_USER", registeredUser.getRoles().get(0).getName());

    }

    @Test
    void testRegisterUser_DuplicateEntityException() {
        User existingUser = new User();
        existingUser.setEmail("existing@example.com");
        existingUser.setName("Existing User");
        existingUser.setPassword("existingPassword");
        when(userRepository.findByField(eq("email"), anyString())).thenReturn(Optional.of(existingUser));
        User newUser = new User();
        newUser.setEmail("existing@example.com");
        newUser.setName("New User");
        newUser.setPassword("newPassword");
        assertThrows(DuplicateEntityException.class, () -> userService.register(newUser));
    }

}
