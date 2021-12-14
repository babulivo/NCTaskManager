package ua.edu.sumdu.j2se.podveza.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Task implements Cloneable{
    private String title;
    private LocalDateTime date;
    private LocalDateTime startDate; // year, month, dayOfMonth, hour, minute
    private LocalDateTime endDate;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, LocalDateTime date) {
        if(date == null) throw new IllegalArgumentException("date is null");

        this.title = title;
        this.date = date;
        this.repeated = false;
    }

    public Task(String title, LocalDateTime startDate, LocalDateTime endDate, int interval) {
        if(startDate == null || endDate == null) throw new IllegalArgumentException("date is null");
        if (interval <= 0) throw new IllegalArgumentException("Interval time is less than or equal to zero");

        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interval = interval;
        this.repeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTime() {
        if (repeated) return startDate;
        return date;
    }

    public void setTime(LocalDateTime time) {
        this.date = time;
        repeated = false;
    }

    public LocalDateTime getStartTime() {
        if (!repeated) return date;
        return startDate;
    }

    public LocalDateTime getEndTime() {
        if (!repeated) return date;
        return endDate;
    }

    public int getRepeatInterval() {
        if (!repeated) return 0;
        return interval;
    }

    public void setTime(LocalDateTime startTime, LocalDateTime endTime, int interval) {
        this.startDate = startTime;
        this.endDate = endTime;
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

    public LocalDateTime nextTimeAfter(LocalDateTime currentDate) {
        if (!isActive()) return null;

        if (repeated) {
            if (startDate.compareTo(currentDate) == 1) {return startDate;}
            LocalDateTime nextTime = startDate;
            while (nextTime.compareTo(currentDate) <= 0) {
                nextTime = nextTime.plusSeconds(interval);
                if (nextTime.compareTo(endDate) > 0) return null;
            }
            return nextTime;
        } else {
            if (date.compareTo(currentDate) == 1) return date;
            else return null;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Task cloned = (Task) super.clone();
        cloned.title = this.title;
        cloned.date = this.date;
        cloned.startDate = this.startDate;
        cloned.endDate = this.endDate;
        cloned.interval = this.interval;
        cloned.active = this.active;
        cloned.repeated = this.repeated;
        return cloned;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", interval=" + interval +
                ", active=" + active +
                ", repeated=" + repeated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return interval == task.interval && active == task.active && repeated == task.repeated &&
                Objects.equals(title, task.title) && Objects.equals(date, task.date) &&
                Objects.equals(startDate, task.startDate) && Objects.equals(endDate, task.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date, startDate, endDate, interval, active, repeated);
    }
}
