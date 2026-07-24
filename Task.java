import java.io.Serializable;

/**
 * Represents a single To-Do item.
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String priority; // High, Medium, Low
    private boolean completed;

    public Task(int id, String title, String priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Converts a task into a single line of text for saving to file.
     * Format: id|title|priority|completed
     */
    public String toFileFormat() {
        return id + "|" + title + "|" + priority + "|" + completed;
    }

    /**
     * Rebuilds a Task object from a saved line of text.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        Task task = new Task(Integer.parseInt(parts[0]), parts[1], parts[2]);
        if (Boolean.parseBoolean(parts[3])) {
            task.markCompleted();
        }
        return task;
    }

    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return String.format("%s ID:%-3d %-30s Priority: %-6s", status, id, title, priority);
    }
}
