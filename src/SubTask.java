

public class SubTask extends Task {
    private final int hashCodeEpic;

    public SubTask(String nameSubTask, String descriptionSubTask, int hashCodeEpic) {
        super(nameSubTask, descriptionSubTask);
        this.hashCodeEpic = hashCodeEpic;
    }


    public SubTask(Task task, int hashCodeEpic) {
        super(task.getTaskName(), task.getTaskDescription(), task.getStatus());
        this.hashCodeEpic = hashCodeEpic;
    }

    public int getHashCodeEpic() {
        return hashCodeEpic;
    }

}
