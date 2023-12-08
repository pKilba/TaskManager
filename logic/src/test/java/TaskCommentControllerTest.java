import com.kilbas.controller.TaskCommentController;
import com.kilbas.controller.TaskController;
import com.kilbas.dto.TaskCommentDto;
import com.kilbas.dto.TaskDto;
import com.kilbas.model.PriorityTask;
import com.kilbas.model.StatusTask;
import com.kilbas.model.Task;
import com.kilbas.model.TaskComment;
import com.kilbas.service.api.TaskCommentService;
import com.kilbas.service.api.TaskService;
import com.kilbas.service.impl.TaskServiceImpl;
import com.kilbas.validator.impl.RequestParametersValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskCommentControllerTest {

    @Mock
    private TaskCommentService taskCommentService;

    @InjectMocks
    private TaskCommentController taskCommentController;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

        @Test
    void testCreateTask_Success() {
            TaskCommentDto taskDto = new TaskCommentDto("test22@mail.ru", "test");
            assertDoesNotThrow(() -> taskCommentController.createTaskComment(1, taskDto));

    }
}
