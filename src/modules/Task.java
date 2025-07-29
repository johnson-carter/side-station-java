package modules;

public class Task {
    private long creationDate;
    private long dueDate;
    private String title;
    private String description;
    private boolean completed;
    private int priority;
    private String type;
    private String[] tags;
    private String[] subtasks;
    private String[] attachments;

    public Task(String title, String description, long dueDate, int priority, String type) {
        this.creationDate = System.currentTimeMillis();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.type = type;
        this.completed = false;
    }
    public void completeTask() {
        this.completed = true;
    }
    public void addTag(String tag) {
        // Logic to add a tag
    }
    public void addSubtask(String subtask) {
        // Logic to add a subtask
    }
    public void addAttachment(String attachment) {
        // Logic to add an attachment
    }
    // Getters and Setters for the fields
    public long getCreationDate() {
        return creationDate;
    }
    public long getDueDate() {
        return dueDate;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public boolean isCompleted() {
        return completed;
    }
    public int getPriority() {
        return priority;
    }
    public String getType() {
        return type;
    }
    public String[] getTags() {
        return tags;
    }
    public String[] getSubtasks() {
        return subtasks;
    }
    public String[] getAttachments() {
        return attachments;
    }
    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setTags(String[] tags) {
        this.tags = tags;
    }
    public void setSubtasks(String[] subtasks) {
        this.subtasks = subtasks;
    }
    public void setAttachments(String[] attachments) {
        this.attachments = attachments;
    }
    @Override
    public String toString() {
        return "Task{" +
                "creationDate=" + creationDate +
                ", dueDate=" + dueDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", priority=" + priority +
                ", type='" + type + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return creationDate == task.creationDate &&
                dueDate == task.dueDate &&
                completed == task.completed &&
                priority == task.priority &&
                title.equals(task.title) &&
                description.equals(task.description) &&
                type.equals(task.type);
    }
    @Override
    public int hashCode() {
        int result = (int) (creationDate ^ (creationDate >>> 32));
        result = 31 * result + (int) (dueDate ^ (dueDate >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (completed ? 1 : 0);
        result = 31 * result + priority;
        result = 31 * result + type.hashCode();
        return result;
    }
}

