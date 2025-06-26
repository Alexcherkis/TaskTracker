package main;

import manager.Managers;
import manager.TaskManager;
import model.*;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        // Создание задач
        Task task1 = new Task("Задача 1", "Описание задачи 1");
        Task task2 = new Task("Задача 2", "Описание задачи 2");

        manager.addNewTask(task1);
        manager.addNewTask(task2);

        Epic epic1 = new Epic("Эпик 1", "Эпик с подзадачами");
        int epicId = manager.addNewEpic(epic1);

        Subtask sub1 = new Subtask("Подзадача 1", "Первая", epicId);
        Subtask sub2 = new Subtask("Подзадача 2", "Вторая", epicId);
        Subtask sub3 = new Subtask("Подзадача 3", "Третья", epicId);

        manager.addNewSubtask(sub1);
        manager.addNewSubtask(sub2);
        manager.addNewSubtask(sub3);

        Epic epic2 = new Epic("Эпик 2", "Эпик без подзадач");
        manager.addNewEpic(epic2);

        // Просмотры
        manager.getTask(task1.getId());
        manager.getEpic(epicId);
        manager.getSubtask(sub1.getId());
        manager.getSubtask(sub2.getId());
        manager.getTask(task2.getId());
        manager.getSubtask(sub3.getId());

        // Повторные просмотры
        manager.getTask(task1.getId());
        manager.getTask(task1.getId()); // ещё раз

        System.out.println("\nИстория после просмотров:");
        manager.getHistory().forEach(System.out::println);

        // Удаляем задачу
        manager.deleteTask(task1.getId());
        System.out.println("\nИстория после удаления задачи 1:");
        manager.getHistory().forEach(System.out::println);

        // Удаляем эпик с подзадачами
        manager.deleteEpic(epicId);
        System.out.println("\nИстория после удаления эпика 1 и подзадач:");
        manager.getHistory().forEach(System.out::println);
    }
}
