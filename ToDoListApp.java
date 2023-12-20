import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        completed = true;
    }
}

class ToDoList {
    private ArrayList<Task> tasks;

    public ToDoList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Task added: " + description);
    }

    public void markTaskAsCompleted(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index);
            task.markAsCompleted();
            System.out.println("Task marked as completed: " + task.getDescription());
        } else {
            System.out.println("Invalid task index");
        }
    }

    public void displayTasks() {
        System.out.println("To-Do List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.isCompleted() ? "[X]" : "[ ]";
            System.out.println(i + ". " + status + " " + task.getDescription());
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}

public class ToDoListApp {
    private static final int ADD_TASK = 1;
    private static final int MARK_COMPLETED = 2;
    private static final int DISPLAY_TASKS = 3;
    private static final int EXIT = 4;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ToDoList toDoList = new ToDoList();

            while (true) {
                printMenu();
                int choice = getChoice(scanner);

                switch (choice) {
                    case ADD_TASK:
                        addTask(scanner, toDoList);
                        break;
                    case MARK_COMPLETED:
                        markTaskAsCompleted(scanner, toDoList);
                        break;
                    case DISPLAY_TASKS:
                        toDoList.displayTasks();
                        break;
                    case EXIT:
                        System.out.println("Exiting the program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Add Task");
        System.out.println("2. Mark Task as Completed");
        System.out.println("3. Display Tasks");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice(Scanner scanner) {
        return scanner.nextInt();
    }

    private static void addTask(Scanner scanner, ToDoList toDoList) {
        System.out.print("Enter task description: ");
        scanner.nextLine(); // Consume the newline character
        String description = scanner.nextLine();
        toDoList.addTask(description);
    }

    private static void markTaskAsCompleted(Scanner scanner, ToDoList toDoList) {
        System.out.print("Enter task index to mark as completed: ");
        int index = scanner.nextInt();
        toDoList.markTaskAsCompleted(index);
    }
}
