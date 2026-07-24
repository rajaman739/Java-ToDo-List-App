# Java To-Do List Application

A simple console-based To-Do List manager built in core Java, with file-based
persistence so tasks are saved between runs.

## Files
- `Task.java` — Model class representing a single task (id, title, priority, completed status)
- `TaskManager.java` — Handles business logic: add, update, delete, mark complete, and file save/load
- `ToDoListApp.java` — Main class with the console menu-driven interface

## How to Run

```bash
cd src
javac *.java
java ToDoListApp
```

Tasks are automatically saved to `tasks.txt` in the same folder after every
change, and reloaded automatically the next time the app starts.

## Features
1. Add Task (with title and priority: High/Medium/Low)
2. View All Tasks
3. Mark Task as Completed
4. Update Task title
5. Delete Task
6. Exit (auto-saves)

## Concepts Used
- Object-Oriented Programming (classes, encapsulation)
- Collections (ArrayList)
- File I/O (BufferedReader/PrintWriter) for persistence
- Exception handling
- Console I/O with Scanner
