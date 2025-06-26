package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TaskManager {

    // Task
    int addNewTask(Task task);
    Task getTask(int id);
    List<Task> getTasks();
    void updateTask(Task task);
    void deleteTask(int id);
    void deleteTasks();

    // Epic
    int addNewEpic(Epic epic);
    Epic getEpic(int id);
    List<Epic> getEpics();
    void updateEpic(Epic epic);
    void deleteEpic(int id);
    void deleteEpics();

    // Subtask
    int addNewSubtask(Subtask subtask);
    Subtask getSubtask(int id);
    List<Subtask> getSubtasks();
    void updateSubtask(Subtask subtask);
    void deleteSubtask(int id);
    void deleteSubtasks();
    List<Task> getEpicSubtasks(int epicId);

    // History
    List<Task> getHistory();
}
