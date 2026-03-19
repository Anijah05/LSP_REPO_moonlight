package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Task objects.
 *
 * Author: Anijah
 */
public class TaskManager {
    private Map<String, Task> tasks;

    /**
     * Constructs an empty TaskManager.
     */
    public TaskManager() {
        tasks = new HashMap<>();
    }

    /**
     * Adds a task to the manager.
     *
     * @param task the task to add
     * @throws IllegalArgumentException if a task with the same ID already exists
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Duplicate task ID");
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds a task by task ID.
     *
     * @param taskId the task ID to search for
     * @return the matching task, or null if not found
     */
    public Task findTask(String taskId) {
        return tasks.get(taskId);
    }

    /**
     * Returns all tasks matching the given status.
     *
     * @param status the status to match
     * @return a list of tasks with the matching status
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                result.add(task);
            }
        }
        return result;
    }
}