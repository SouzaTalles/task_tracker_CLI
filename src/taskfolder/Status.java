package taskfolder;

public enum Status {
    TODO,
    IN_PROGRESS,
    DONE;

    public static Status fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        return switch (value.toLowerCase()) {
            case "todo" -> TODO;
            case "in-progress", "in_progress", "inprogress" -> IN_PROGRESS;
            case "done" -> DONE;
            default -> throw new IllegalArgumentException("Invalid status: " + value);
        };
    }
}
