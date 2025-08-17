package repository;

import taskfolder.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    private static final Path FILE_PATH = Path.of("tasks.json");
    private List<Task> tasks = new ArrayList<>();

    public TaskRepository() throws IOException {
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
            System.out.println(" -==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-=");
            System.out.println("|                                                    WELCOME TO TASK MANAGER                                                     |");
            System.out.println(" -==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-=");

        } else {
            System.out.println(" -==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-=");
            System.out.println("|                                                           TASK MANAGER                                                         |");
            System.out.println(" -==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-==-=");
            loadTasksFromFile();
        }
    }

    public void saveTasksToFile() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append("\n{\n\"id\":            ").append(task.getId())
                    .append(",\n\"description\":     \"").append(task.getDescription()).append("\"")
                    .append(",\n\"status\":          \"").append(task.getStatus()).append("\"")
                    .append(",\n\"createdAt\":       \"").append(task.getCreatedAt().format(Task.formatter)).append("\"")
                    .append(",\n\"updatedAt\":       \"").append(task.getUpdatedAt().format(Task.formatter)).append("\"\n}");
            if (i < tasks.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("\n]");

        try {
            Files.writeString(FILE_PATH, sb.toString());
        } catch (IOException e) {
            System.out.println(" * [ERRO] Erro ao salvar arquivo: " + e.getMessage());
        }
    }


    public String readJsonFile() throws IOException {
        Path filePath = Path.of("tasks.json");
            return Files.readString(filePath);
    }

    public void loadTasksFromFile() {
        tasks.clear();

        try {
            String content = readJsonFile().trim();
            if (content.isEmpty() || content.length() < 2) {
                return;
            }

            java.util.regex.Pattern taskPattern = java.util.regex.Pattern.compile("\\{\\s*\"id\":\\s*(\\d+),\\s*\"description\":\\s*\"(.*?)\",\\s*\"status\":\\s*\"(.*?)\",\\s*\"createdAt\":\\s*\"(.*?)\",\\s*\"updatedAt\":\\s*\"(.*?)\"\\s*\\}");
            java.util.regex.Matcher matcher = taskPattern.matcher(content);

            while (matcher.find()) {
                int id = Integer.parseInt(matcher.group(1));
                String description = matcher.group(2);
                String status = matcher.group(3);
                String createdAtStr = matcher.group(4);
                String updatedAtStr = matcher.group(5);

                LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, Task.formatter);
                LocalDateTime updatedAt = LocalDateTime.parse(updatedAtStr, Task.formatter);

                tasks.add(new Task(id, description, status, createdAt, updatedAt));
            }

        } catch (IOException e) {
            System.out.println("* [ERRO] Erro ao carregar tarefas: " + e.getMessage());
        }
    }

    public Task add(Task task) {
        tasks.add(task);
        saveTasksToFile();
        return task;
    }

    public void update(Task task) {
        findById(task.getId()).ifPresent(existingTask -> {
            existingTask.setDescription(task.getDescription());
            existingTask.setUpdatedAt(task.getUpdatedAt());
            saveTasksToFile();
        });
    }

    public void updateStatus(Task task) {
        findById(task.getId()).ifPresent(existingTask -> {
            existingTask.setStatus(task.getStatus());
            existingTask.setUpdatedAt(task.getUpdatedAt());
            saveTasksToFile();
        });
    }

    public void delete(Task task) {
        tasks.remove(task);
        saveTasksToFile();
    }

    public Optional<Task> findById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    public List<Task> findByStatus(String status) {
        return tasks.stream()
                .filter(task -> task.getStatus().name().replace('_', '-').equalsIgnoreCase(status))
                .toList();
    }

    public int findMaxId() {
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0);
    }
}
