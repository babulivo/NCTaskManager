package ua.edu.sumdu.j2se.podveza.tasks;

public class Main {

	public static void main(String[] args) {
		Task task1 = new Task("Football", 1);
		Task task2 = new Task("Swimming", 2);
		Task task3 = new Task("Playing PC", 3);
		Task task4 = new Task("Date", 4);
		Task task5 = new Task("Football", 1);

		LinkedTaskList linkedTaskList = new LinkedTaskList();

		System.out.println(linkedTaskList.size());

		linkedTaskList.add(task1);
		linkedTaskList.add(task2);
		linkedTaskList.add(task3);
		linkedTaskList.add(task4);
		linkedTaskList.add(task5);

		System.out.println(linkedTaskList.size());

		linkedTaskList.remove(task1);
		linkedTaskList.remove(task4);
		linkedTaskList.remove(task3);
		linkedTaskList.remove(task1);
		linkedTaskList.remove(task2);

		System.out.println(linkedTaskList.size());

		linkedTaskList.add(task1);
		linkedTaskList.add(task2);
		linkedTaskList.add(task2);
		linkedTaskList.add(task2);
		linkedTaskList.add(task2);
		linkedTaskList.add(task2);
		linkedTaskList.add(task2);
		linkedTaskList.add(task2);

		System.out.println(linkedTaskList.size());
	}
}
