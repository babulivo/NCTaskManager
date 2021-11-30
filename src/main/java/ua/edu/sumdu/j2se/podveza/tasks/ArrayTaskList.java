package ua.edu.sumdu.j2se.podveza.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayTaskList extends AbstractTaskList{
    private int realSize = 3;
    private Task[] taskList = new Task[realSize];

    public ArrayTaskList() {
        listType = ListTypes.types.ARRAY;
    }

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

    @Override
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null");
        }

        if (size != 0 && size == realSize)
            resize("increase");
        taskList[size] = task;
        size++;
    }

    @Override
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

    @Override
    public Task getTask(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("There is no element with this index");
        } else {
            return taskList[index];
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            private int currentIndex;
            private int removed;

            @Override
            public boolean hasNext() {
                return currentIndex < size && taskList[currentIndex] != null;
            }

            @Override
            public Task next() {
                if (hasNext()) {
                    removed = currentIndex;
                    return taskList[currentIndex++];
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                if (currentIndex == 0) {
                    throw new IllegalStateException();
                }
                ArrayTaskList.this.remove(taskList[removed]);
                --currentIndex;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return realSize == that.realSize && Arrays.equals(taskList, that.taskList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(realSize);
        result = 31 * result + Arrays.hashCode(taskList);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /*@Override
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
    }*/
}
