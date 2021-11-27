package ua.edu.sumdu.j2se.podveza.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
        switch (type) {
            case ARRAY:
                ArrayTaskList arrayTaskList = new ArrayTaskList();
                return arrayTaskList;
            case LINKED:
                LinkedTaskList linkedTaskList = new LinkedTaskList();
                return linkedTaskList;
            default:
                return null;
        }
    }
}
