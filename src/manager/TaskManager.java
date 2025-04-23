package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TaskManager {
    // Методы для работы с Task
    List<Task> getTasks();
    Task getTask(int id);
    int addNewTask(Task task);
    void updateTask(Task task);
    void deleteTask(int id);
    void deleteTasks();

    // Методы для работы с Epic
    List<Epic> getEpics();
    Epic getEpic(int id);
    int addNewEpic(Epic epic);
    void updateEpic(Epic epic);
    void deleteEpic(int id);
    void deleteEpics();

    // Методы для работы с Subtask
    List<Subtask> getSubtasks();
    Subtask getSubtask(int id);
    List<Task> getEpicSubtasks(int epicId);
    int addNewSubtask(Subtask subtask);
    void updateSubtask(Subtask subtask);
    void deleteSubtask(int id);
    void deleteSubtasks();

    // Метод для истории просмотров
    List<Task> getHistory();
}