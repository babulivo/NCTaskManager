package ua.edu.sumdu.j2se.podveza.tasks;

import java.util.*;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList{
    private TaskNode head;

    public class TaskNode {
       private Task task;
       private TaskNode next;

       public TaskNode(Task task) {
           this.task = task;
           next = null;
       }
   }

    public LinkedTaskList() {
        listType = ListTypes.types.LINKED;
    }

    @Override
    public void add(Task task) {
        if (task == null) {throw new IllegalArgumentException("Task must not be null");}

        TaskNode newTaskNode = new TaskNode(task);
        TaskNode currentTaskNode = head;

        if (head == null) {
            head = newTaskNode;
        } else {
            while (currentTaskNode.next != null) {
                currentTaskNode = currentTaskNode.next;
            }
            currentTaskNode.next = newTaskNode;
        }
        ++size;
    }

    @Override
    public boolean remove(Task task) {
        TaskNode currentTaskNode = head;
        TaskNode previousTaskNode = null;

        for (int i = 0; i < size; i++) {
            if (currentTaskNode.task.equals(task)) {
                if (currentTaskNode == head) {
                    head = currentTaskNode.next;
                } else {
                    previousTaskNode.next = currentTaskNode.next;
                }
                --size;
                return true;
            }
            previousTaskNode = currentTaskNode;
            currentTaskNode = currentTaskNode.next;
        }
        return false;
    }

    @Override
    public Task getTask(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("There is no element with this index");
        } else {
            TaskNode currentTaskNode = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {return currentTaskNode.task;}
                currentTaskNode = currentTaskNode.next;
            }
        }
        return null;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            private int currentIndex;
            private int removed;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Task next() {
                if (hasNext()) {
                    removed = currentIndex;
                    return getTask(currentIndex++);
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                if (currentIndex < 1) {
                    throw new IllegalStateException();
                }
                LinkedTaskList.this.remove(getTask(removed));
                --currentIndex;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList tasks = (LinkedTaskList) o;
        for (int i = 0; i < size; i++) {
            if(!tasks.getTask(i).equals(getTask(i))) return false;
        }
        return size == tasks.size;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        for (int i = 0; i < size; i++) {
            hash = 31 * hash + ((getTask(i) == null) ? 0 : getTask(i).hashCode());
        }
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Stream<Task> getStream() {
        List<Task> streamList = new LinkedList<Task>();
        for (int i = 0; i < size; i++) {
            streamList.add(getTask(i));
        }
        return streamList.stream();
    }

    /*@Override
    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList incomingList = new LinkedTaskList();
        TaskNode currentTaskNode = head;

        for (int i = 0; i < size; i++) {
            int nextTime = currentTaskNode.task.nextTimeAfter(from);
            if (nextTime != -1 && nextTime < to) {
                incomingList.add(currentTaskNode.task);
            }
            currentTaskNode = currentTaskNode.next;
        }
        return incomingList;
    }*/
}
