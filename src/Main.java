import manager.InMemoryTaskManager;
import manager.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new InMemoryTaskManager();

        System.out.println("=== Создание задач ===");

        // Создаем две задачи
        Task task1 = new Task("Задача 1", "Описание задачи 1");
        Task task2 = new Task("Задача 2", "Описание задачи 2");

        manager.createTask(task1);
        manager.createTask(task2);

        // Создаем эпик с двумя подзадачами
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача 1.1", "Описание подзадачи 1.1", epic1.getId());
        Subtask subtask2 = new Subtask("Подзадача 1.2", "Описание подзадачи 1.2", epic1.getId());

        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);

        // Создаем эпик с одной подзадачей
        Epic epic2 = new Epic("Эпик 2", "Описание эпика 2");
        manager.createEpic(epic2);

        Subtask subtask3 = new Subtask("Подзадача 2.1", "Описание подзадачи 2.1", epic2.getId());

        manager.createSubtask(subtask3);

        // Выводим созданные задачи
        System.out.println("\n=== Список задач ===");
        System.out.println(manager.getAllTasks());

        System.out.println("\n=== Список эпиков ===");
        System.out.println(manager.getAllEpics());

        System.out.println("\n=== Список подзадач ===");
        System.out.println(manager.getAllSubtasks());

        // Обновляем статусы
        System.out.println("\n=== Обновление статусов ===");

        // Обновляем статус задачи
        task1.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateTask(task1);

        // Обновляем статусы подзадач
        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);

        subtask2.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask2);

        subtask3.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subtask3);

        // Выводим обновленные задачи
        System.out.println("\n=== Обновленный список задач ===");
        System.out.println(manager.getAllTasks());

        System.out.println("\n=== Обновленный список эпиков ===");
        System.out.println(manager.getAllEpics());

        System.out.println("\n=== Обновленный список подзадач ===");
        System.out.println(manager.getAllSubtasks());

        // Удаляем задачу и эпик
        System.out.println("\n=== Удаление ===");
        manager.removeTaskById(task1.getId());
        manager.removeEpicById(epic1.getId());

        // Выводим финальные списки
        System.out.println("\n=== Финальный список задач ===");
        System.out.println(manager.getAllTasks());

        System.out.println("\n=== Финальный список эпиков ===");
        System.out.println(manager.getAllEpics());

        System.out.println("\n=== Финальный список подзадач ===");
        System.out.println(manager.getAllSubtasks());
    }
}
