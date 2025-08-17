package taskfolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Task(int id, String description, String statusStr, LocalDateTime createdAd, LocalDateTime updatedAd) {
        this.id = id;
        this.description = description;
        this.status = Status.fromString(statusStr);
        this.createdAt = createdAd;
        this.updatedAt = updatedAd;
    }

    @Override
    public String toString() {
        return String.format(
                "| ID: %-2d | Status: %-11s | Description: %-20s | Created At: %-16s | Updated At: %-16s |",
                id, status, description, createdAt.format(formatter), updatedAt.format(formatter)
        );
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
