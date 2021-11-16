package ua.edu.sumdu.j2se.podveza.tasks;

public class ArrayTaskList {
    private int size = 0;
    private int realSize = 3;
    private Task[] taskList = new Task[realSize];

    private void resize(String condition){
        Task[] copyTaskList;
        if(condition == "increase") {
            if(size != 1) copyTaskList = new Task[(int)(realSize *= 1.5)];
            else copyTaskList = new Task[realSize = 2];
            for (int i = 0; i < size; i++) {
                copyTaskList[i] = taskList[i];
            }
            taskList = copyTaskList;
        } else if (condition == "decrease") {
            copyTaskList = new Task[size];
            for (int i = 0; i < size; i++) {
                copyTaskList[i] = taskList[i];
            }
            realSize = size;
            taskList = copyTaskList;
        }
    }

    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null");
        }

        if (size != 0 && size == realSize)
            resize("increase");
        taskList[size] = task;
        size++;
    }

    public boolean remove(Task task) {
        for (int i = 0; i < size; i++) {
            if(taskList[i].equals(task)) { // equals
                --size;
                taskList[i] = null;
                while (i < size) {
                    taskList[i] = taskList[i+1];
                    i++;
                }
                if (size != 0 && realSize / size == 2)
                    resize("decrease");
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Task getTask(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("There is no element with this index");
        } else {
            return taskList[index];
        }
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList incomingList = new ArrayTaskList();
        for (Task fromTaskList: taskList) {
            if (fromTaskList != null) {
                int nextTime = fromTaskList.nextTimeAfter(from);
                if (nextTime != -1 && nextTime < to) {
                    incomingList.add(fromTaskList);
                }
            }
        }
        return incomingList;
    }
}
