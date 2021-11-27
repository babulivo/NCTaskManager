package ua.edu.sumdu.j2se.podveza.tasks;

public abstract class AbstractTaskList {
    protected int size;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public int size(){return size;};

    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList incomingList = this.getClass().getSimpleName().equals("LinkedTaskList") ?
                TaskListFactory.createTaskList(ListTypes.types.LINKED) :
                TaskListFactory.createTaskList(ListTypes.types.ARRAY);

        Task task;
        for (int i = 0; i < size; i++) {
            task = getTask(i);
            int nextTime = task.nextTimeAfter(from);
            if (nextTime != -1 && nextTime < to) {
                incomingList.add(task);
            }
        }
        return incomingList;
    }
}
