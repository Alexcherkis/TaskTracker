package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TaskManager {
    // Методы для работы с Task
    List<Task> getAllTasks();
    Task getTaskById(int id);
    void createTask(Task task);
    void updateTask(Task task);
    void removeTaskById(int id);
    void removeAllTasks();

    // Методы для работы с Epic
    List<Epic> getAllEpics();
    Epic getEpicById(int id);
    void createEpic(Epic epic);
    void updateEpic(Epic epic);
    void removeEpicById(int id);
    void removeAllEpics();

    // Методы для работы с Subtask
    List<Subtask> getAllSubtasks();
    Subtask getSubtaskById(int id);
    List<Subtask> getSubtasksByEpicId(int epicId);
    void createSubtask(Subtask subtask);
    void updateSubtask(Subtask subtask);
    void removeSubtaskById(int id);
    void removeAllSubtasks();
}
