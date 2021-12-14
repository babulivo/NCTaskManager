package ua.edu.sumdu.j2se.podveza.tasks;

import java.util.*;
import java.time.LocalDateTime;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) throw new NullPointerException();
        if (start.isAfter(end)) throw new IllegalArgumentException("Start is after end");
        
        List<Task> incomingList = new LinkedList<>();

        for (Task task : tasks) {
            if (task.nextTimeAfter(start) != null && task.nextTimeAfter(start).compareTo(end) <= 0) {
                incomingList.add(task);
            }
        }
        return incomingList;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar
            (Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
        LocalDateTime time;

        for (Task task : tasks) {
            time = task.nextTimeAfter(start);
            while (time != null && time.compareTo(end) <= 0){
                if (calendar.containsKey(time)) {
                    calendar.get(time).add(task);
                } else {
                    Set<Task> taskSet = new HashSet<>();
                    taskSet.add(task);
                    calendar.put(time, taskSet);
                }
                time = task.nextTimeAfter(time);
            }
        }
        return calendar;
    }
}
