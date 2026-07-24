import java.util.List;
import java.util.Scanner;

/**
 * Console-based To-Do List Application.
 * Allows a user to add, view, update, delete, and complete tasks.
 * Tasks are persisted to a local text file so data survives restarts.
 */
public class ToDoListApp {
    private static TaskManager taskManager = new TaskManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=====================================");
        System.out.println("      WELCOME TO JAVA TO-DO LIST      ");
        System.out.println("=====================================");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    viewTasks();
                    break;
                case "3":
                    markCompleted();
                    break;
                case "4":
                    updateTask();
                    break;
                case "5":
                    deleteTask();
                    break;
                case "6":
                    running = false;
                    System.out.println("Goodbye! Your tasks have been saved.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1-6.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. Update Task");
        System.out.println("5. Delete Task");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Task title cannot be empty.");
            return;
        }
        System.out.print("Enter priority (High/Medium/Low): ");
        String priority = scanner.nextLine().trim();
        if (priority.isEmpty()) {
            priority = "Medium";
        }
        Task task = taskManager.addTask(title, priority);
        System.out.println("Task added successfully with ID: " + task.getId());
    }

    private static void viewTasks() {
        List<Task> tasks = taskManager.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found. Add a task to get started!");
            return;
        }
        System.out.println("\n----------- YOUR TASKS -----------");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static void markCompleted() {
        viewTasks();
        System.out.print("Enter task ID to mark as completed: ");
        int id = readInt();
        if (id == -1) return;
        if (taskManager.markTaskCompleted(id)) {
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void updateTask() {
        viewTasks();
        System.out.print("Enter task ID to update: ");
        int id = readInt();
        if (id == -1) return;
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine().trim();
        if (taskManager.updateTask(id, newTitle)) {
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void deleteTask() {
        viewTasks();
        System.out.print("Enter task ID to delete: ");
        int id = readInt();
        if (id == -1) return;
        if (taskManager.deleteTask(id)) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered.");
            return -1;
        }
    }
}
