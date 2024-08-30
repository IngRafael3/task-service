package com.example.taskservice.TaskServiceImplTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import com.example.taskservice.dto.TaskDTO;
import com.example.taskservice.models.TaskEntity;
import com.example.taskservice.repository.TaskRepository;
import com.example.taskservice.service.impl.TaskServiceImpl;
import com.example.taskservice.utils.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTaskById_ShouldReturnTaskDTO_WhenTaskExists() {
        TaskEntity taskEntity = new TaskEntity("Title", "Description", "status", "user@example.com");

        TaskDTO expectedTaskDTO = TaskMapper.toTaskDTO(taskEntity);

        when(taskRepository.findById(1L)).thenReturn(Mono.just(taskEntity));

        StepVerifier.create(taskService.getTaskById(1L))
                .consumeNextWith(taskDTO -> assertThat(taskDTO).isEqualTo(expectedTaskDTO))
                .expectComplete()
                .verify();
    }

    @Test
    void getAllTasks_ShouldReturnListOfTaskDTOs() {
        TaskEntity taskEntity1 = new TaskEntity( "Title1", "Description1", "status1", "user1@example.com");
        TaskEntity taskEntity2 = new TaskEntity("Title2", "Description2", "status2", "user2@example.com");
        TaskDTO taskDTO1 = new TaskDTO("Title1", "Description1", "status1", "user1@example.com");
        TaskDTO taskDTO2 = new TaskDTO("Title2", "Description2", "status2", "user2@example.com");

        when(taskRepository.findAll()).thenReturn(Flux.just(taskEntity1, taskEntity2));

        Flux<TaskDTO> result = taskService.getAllTasks();

        StepVerifier.create(result)
                .expectNext(taskDTO1, taskDTO2)
                .verifyComplete();
    }

    @Test
    void createTask_ShouldReturnCreatedTaskDTO() {
        TaskDTO taskDTO = new TaskDTO( "Title", "Description", "status", "user@example.com");
        TaskEntity taskEntity = new TaskEntity("Title", "Description", "status", "user@example.com");
        TaskEntity savedTaskEntity = new TaskEntity("Title", "Description", "status", "user@example.com");
        TaskDTO savedTaskDTO = new TaskDTO( "Title", "Description", "status", "user@example.com");

        when(taskRepository.save(taskEntity)).thenReturn(Mono.just(savedTaskEntity));

        Mono<TaskDTO> result = taskService.createTask(taskDTO);

        StepVerifier.create(result)
                .expectNext(savedTaskDTO)
                .verifyComplete();
    }

    @Test
    void updateTask_ShouldReturnUpdatedTaskDTO() {
        TaskEntity existingTaskEntity = new TaskEntity("Old Title", "Old Description", "Old Status", "olduser@example.com");
        TaskDTO updatedTaskDTO = new TaskDTO("New Title", "New Description", "New Status", "newuser@example.com");
        TaskEntity updatedTaskEntity = new TaskEntity( "New Title", "New Description", "New Status", "newuser@example.com");

        when(taskRepository.findById(1L)).thenReturn(Mono.just(existingTaskEntity));
        when(taskRepository.save(existingTaskEntity)).thenReturn(Mono.just(updatedTaskEntity));

        Mono<TaskDTO> result = taskService.updateTask(1L, updatedTaskDTO);

        StepVerifier.create(result)
                .expectNext(updatedTaskDTO)
                .verifyComplete();
    }

    @Test
    void deleteTask_ShouldCompleteSuccessfully_WhenTaskExists() {
        TaskEntity taskEntity = new TaskEntity("Title", "Description", "status", "user@example.com");

        when(taskRepository.findById(1L)).thenReturn(Mono.just(taskEntity));
        when(taskRepository.delete(taskEntity)).thenReturn(Mono.empty());

        Mono<Void> result = taskService.deleteTask(1L);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void findByUserEmail_ShouldReturnTasksForGivenEmail() {
        TaskEntity taskEntity = new TaskEntity("Title", "Description", "status", "user@example.com");
        TaskDTO taskDTO = new TaskDTO("Title", "Description", "status", "user@example.com");

        when(taskRepository.findByUserEmail("user@example.com")).thenReturn(Flux.just(taskDTO));

        Flux<TaskDTO> result = taskService.findByUserEmail("user@example.com");

        StepVerifier.create(result)
                .expectNext(taskDTO)
                .verifyComplete();
    }
}

