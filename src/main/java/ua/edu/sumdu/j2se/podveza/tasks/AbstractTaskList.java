package ua.edu.sumdu.j2se.podveza.tasks;

import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>{
    protected ListTypes.types listType;
    protected int size;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public int size(){return size;}

    public abstract Task getTask(int index);

    public final AbstractTaskList incoming(int from, int to) {
        AbstractTaskList incomingList = TaskListFactory.createTaskList(listType);
        Stream<Task> stream = getStream();

        stream.filter(Task -> Task.nextTimeAfter(from) != -1 && Task.nextTimeAfter(from) < to)
                .forEach(Task -> incomingList.add(Task));
        return incomingList;

        /*AbstractTaskList incomingList = TaskListFactory.createTaskList(listType);
        Task task;
        for (int i = 0; i < size; i++) {
            task = getTask(i);
            int nextTime = task.nextTimeAfter(from);
            if (nextTime != -1 && nextTime < to) {
                incomingList.add(task);
            }
        }
        return incomingList;*/
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AbstractTaskList clonedList = TaskListFactory.createTaskList(listType);
        for (int i = 0; i < size; i++) {
            clonedList.add(getTask(i));
        }
        return clonedList;
    }

    public abstract Stream<Task> getStream();
}
