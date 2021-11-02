package ua.edu.sumdu.j2se.podveza.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean isRepeated;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.isRepeated = false;
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.isRepeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        if (isRepeated) return start;
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        isRepeated = false;
    }

    public int getStartTime() {
        if (!isRepeated) return time;
        return start;
    }

    public int getEndTime() {
        if (!isRepeated) return time;
        return end;
    }

    public int getRepeatInterval() {
        if (!isRepeated) return 0;
        return interval;
    }

    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.isRepeated = true;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int nextTimeAfter(int current) {
        if (!isActive()) return -1;

        if (isRepeated) {

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
