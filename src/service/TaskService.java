package service;

import repository.TaskRepository;
import taskfolder.Status;
import taskfolder.Task;

import java.time.LocalDateTime;
import java.util.List;

public class TaskService {
    TaskRepository repository;

    public TaskService() {
        try {
            repository = new TaskRepository();
        } catch (Exception e) {
            throw new RuntimeException(" [ERRO] Erro ao inicializar o repositório de tarefas", e);
        }
    }

    public void add(String description) {
        Task task = new Task(
                repository.findMaxId() + 1,
                description,
                "todo",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        repository.add(task);
    }

    public void update(int id, String description) {
        Task task = repository.findById(id).orElseThrow();
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());
        repository.update(task);
    }

    public void updateStatus(int id, Status status) {
        Task task = repository.findById(id).orElseThrow();
        if (task == null) {
            System.out.println("[ERRO] Nenhuma tarefa encontrada com ID " + id);
            line();
            return;
        }

        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());
        repository.updateStatus(task);
        System.out.println("[INFO] Tarefa " + id + " atualizada com sucesso!");
        line();
    }

    public void delete(int id) {
        Task task = repository.findById(id).orElseThrow();
        repository.delete(task);
    }

    public boolean checkInputId(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean checkInputDescription(String input) {
        return input.isBlank();
    }

    public List<Task> listAllTasks() {
        return repository.findAll();
    }

    public List<Task> listTasksByStatus(String status) {
        return repository.findByStatus(status);
    }

    public void line() {
        System.out.println(" -==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-=");
    }
}
