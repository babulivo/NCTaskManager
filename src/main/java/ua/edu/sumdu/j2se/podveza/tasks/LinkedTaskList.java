package ua.edu.sumdu.j2se.podveza.tasks;

public class LinkedTaskList extends AbstractTaskList{
    //private int size;
    private TaskNode head;

   public class TaskNode {
       private Task task;
       private TaskNode next;

       public TaskNode(Task task) {
           this.task = task;
           next = null;
       }
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

    //public int size() {return size;}

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
