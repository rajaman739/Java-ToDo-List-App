import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the storage and manipulation of tasks, and manages
 * loading/saving tasks to a text file for persistence.
 */
public class TaskManager {
    private List<Task> tasks;
    private int nextId;
    private static final String FILE_NAME = "tasks.txt";

    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
        loadFromFile();
    }

    public Task addTask(String title, String priority) {
        Task task = new Task(nextId++, title, priority);
        tasks.add(task);
        saveToFile();
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public boolean markTaskCompleted(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markCompleted();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    public boolean updateTask(int id, String newTitle) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(newTitle);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    /** Persists the current task list to a text file. */
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /** Loads tasks from the text file when the application starts. */
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int maxId = 0;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Task task = Task.fromFileFormat(line);
                tasks.add(task);
                maxId = Math.max(maxId, task.getId());
            }
            nextId = maxId + 1;
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
