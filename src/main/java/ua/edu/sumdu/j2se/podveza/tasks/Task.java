package ua.edu.sumdu.j2se.podveza.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.repeated = false;
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        if (repeated) return start;
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        repeated = false;
    }

    public int getStartTime() {
        if (!repeated) return time;
        return start;
    }

    public int getEndTime() {
        if (!repeated) return time;
        return end;
    }

    public int getRepeatInterval() {
        if (!repeated) return 0;
        return interval;
    }

    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int nextTimeAfter(int current) {
        if (!isActive()) return -1;

        if (repeated) {

            if (start > current) {
                return start;
            }
            int nextTime = start;
            while (nextTime <= current) {
                nextTime += interval;
                if (nextTime >= end) return -1;
            }
            return nextTime;

        } else {
            if (time > current) return time;
            else return -1;
        }
    }
}
