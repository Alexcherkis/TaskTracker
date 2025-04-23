package main;

import manager.Managers;
import manager.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        // Проверка равенства задач по ID
        System.out.println("=== Проверка равенства задач по ID ===");
        Task task1 = new Task("Задача 1", "Описание 1");
        task1.setId(1);
        Task task2 = new Task("Задача 2", "Описание 2");
        task2.setId(1);
        System.out.println("Задачи равны: " + task1.equals(task2)); // Должно быть true

        // Проверка равенства эпиков по ID
        System.out.println("\n=== Проверка равенства эпиков по ID ===");
        Epic epic1 = new Epic("Эпик 1", "Описание 1");
        epic1.setId(1);
        Epic epic2 = new Epic("Эпик 2", "Описание 2");
        epic2.setId(1);
        System.out.println("Эпики равны: " + epic1.equals(epic2)); // Должно быть true

        // Проверка равенства подзадач по ID
        System.out.println("\n=== Проверка равенства подзадач по ID ===");
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание 1", 1);
        subtask1.setId(1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание 2", 1);
        subtask2.setId(1);
        System.out.println("Подзадачи равны: " + subtask1.equals(subtask2)); // Должно быть true

        // Проверка, что эпик не может быть своей подзадачей
        System.out.println("\n=== Проверка, что эпик не может быть своей подзадачей ===");
        Epic epic3 = new Epic("Эпик 3", "Описание 3");
        int epicId = manager.addNewEpic(epic3);
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание 3", epicId);
        subtask3.setId(epicId); // Пытаемся установить одинаковый ID
        int subtaskId = manager.addNewSubtask(subtask3); // Метод должен изменить ID подзадачи
        System.out.println("ID эпика: " + epicId);
        System.out.println("ID подзадачи: " + subtask3.getId());
        System.out.println("Эпик и подзадача имеют одинаковый ID: " + (epicId == subtask3.getId())); // Должно быть false

        // Проверка, что подзадача не может быть своим эпиком
        System.out.println("\n=== Проверка, что подзадача не может быть своим эпиком ===");
        Subtask subtask4 = new Subtask("Подзадача 4", "Описание 4", subtask1.getId());
        System.out.println("Подзадача является своим эпиком: " + (subtask4.getId() == subtask4.getEpicId())); // Должно быть false

        // Проверка добавления и поиска задач
        System.out.println("\n=== Проверка добавления и поиска ===");
        Task task3 = new Task("Задача 3", "Описание 3");
        int taskId = manager.addNewTask(task3);
        Task foundTask = manager.getTask(taskId);
        System.out.println("Задача найдена: " + (foundTask != null && foundTask.equals(task3))); // Должно быть true

        // Проверка конфликта ID
        System.out.println("\n=== Проверка конфликта ID ===");
        Task task4 = new Task("Задача 4", "Описание 4");
        task4.setId(taskId);
        manager.addNewTask(task4);
        System.out.println("Количество задач: " + manager.getTasks().size()); // Должно быть 2

        // Проверка истории (создаём 12 задач, чтобы проверить лимит)
        System.out.println("\n=== Проверка истории ===");
        Task[] tasks = new Task[12];
        for (int i = 0; i < 12; i++) {
            tasks[i] = new Task("Задача " + (i + 1), "Описание " + (i + 1));
            manager.addNewTask(tasks[i]);
        }
        Epic epic4 = new Epic("Эпик 4", "Описание 4");
        manager.addNewEpic(epic4);
        Subtask subtask5 = new Subtask("Подзадача 5", "Описание 5", epic4.getId());
        manager.addNewSubtask(subtask5);

        // Вызываем методы для добавления в историю
        for (int i = 0; i < 12; i++) {
            manager.getTask(tasks[i].getId());
        }
        manager.getEpic(epic4.getId());
        manager.getSubtask(subtask5.getId());

        // Выводим результат
        printAllTasks(manager);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("\n=== Итоговый вывод ===");
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpics()) {
            System.out.println(epic);
            for (Task task : manager.getEpicSubtasks(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }
        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}