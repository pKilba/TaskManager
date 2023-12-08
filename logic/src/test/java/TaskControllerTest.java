import com.kilbas.controller.TaskController;
import com.kilbas.dto.TaskDto;
import com.kilbas.model.PriorityTask;
import com.kilbas.model.StatusTask;
import com.kilbas.model.Task;
import com.kilbas.service.impl.TaskServiceImpl;
import com.kilbas.validator.impl.RequestParametersValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class TaskControllerTest {

    @Mock
    private TaskServiceImpl taskService;

    @Mock
    private RequestParametersValidator requestParametersValidator;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Task task = new Task();
        when(taskService.create(any(Task.class))).thenReturn(task);

        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(1, "Task 1", "Description for Task 1",
                StatusTask.PROGRESS, PriorityTask.HIGH, "John Doe", "Alice");

        tasks.add(task1);
        when(taskService.findAllWithFiltering(anyString(), anyString(), anyInt(), anyInt())).thenReturn(tasks);
    }

    @Test
    void testCreateTask_Success() {
        TaskDto taskDto = new TaskDto();
        Task result = taskController.create(taskDto);

        assertNotNull(result);
    }

    @Test
    void testFindAllTasks_Success() {



        List<Task> result = taskController.findAll("John Doe", "Alice", 1, 10);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

}
