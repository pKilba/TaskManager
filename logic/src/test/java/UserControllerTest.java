import com.kilbas.controller.UserController;
import com.kilbas.exception.NotFoundEntityException;
import com.kilbas.model.User;
import com.kilbas.service.impl.UserServiceImpl;
import com.kilbas.validator.impl.RequestParametersValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private RequestParametersValidator requestParametersValidator;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<User> users = new ArrayList<>();
        users.add(new User(1, "John"));
        users.add(new User(2, "Alice"));

        when(userService.findAll(anyInt(), anyInt())).thenReturn(users);
        when(userService.findById(anyLong())).thenReturn(new User(1, "John"));
    }


    @Test
    void testFindAllUsers_Success() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John"));
        users.add(new User(2, "Alice"));
        when(userService.findAll(anyInt(), anyInt())).thenReturn(users);
        List<User> result = userController.findAll(1, 10);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testFindUserById_Success() throws NotFoundEntityException {
        User user = new User(1, "John");
        when(userService.findById(anyLong())).thenReturn(user);
        User result = userController.findById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John", result.getName());
    }
}
