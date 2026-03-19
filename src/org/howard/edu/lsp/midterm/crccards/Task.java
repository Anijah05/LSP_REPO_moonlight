package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a task in the task management system.
 *
 * Author: Anijah
 */
public class Task {
    private String taskId;
    private String description;
    private String status;

    /**
     * Constructs a Task with default status OPEN.
     *
     * @param taskId the unique task ID
     * @param description the task description
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
    }

    /**
     * Returns the task ID.
     *
     * @return the task ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the current task status.
     *
     * @return the task status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the task status. Valid values are OPEN, IN_PROGRESS, and COMPLETE.
     * Any other value sets the status to UNKNOWN.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        if ("OPEN".equals(status) || "IN_PROGRESS".equals(status) || "COMPLETE".equals(status)) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns the task as a formatted string.
     *
     * @return formatted task string
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}