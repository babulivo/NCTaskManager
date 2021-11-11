package ua.edu.sumdu.j2se.podveza.tasks;

public class Main {

	public static void main(String[] args) {
		Task task1 = new Task("Football", 1);
		Task task2 = new Task("Swimming", 2);
		Task task3 = new Task("Playing PC", 3);
		Task task4 = new Task("Date", 4);

		ArrayTaskList arrayTaskList = new ArrayTaskList();

		System.out.println(arrayTaskList.size());

		arrayTaskList.add(task1);
		arrayTaskList.add(task2);
		arrayTaskList.add(task3);
		arrayTaskList.add(task4);

		System.out.println(arrayTaskList.size());

		arrayTaskList.remove(task3);
		arrayTaskList.remove(task4);
		arrayTaskList.remove(task1);
		arrayTaskList.remove(task2);

		System.out.println(arrayTaskList.size());

		arrayTaskList.add(task1);
		arrayTaskList.add(task2);
		arrayTaskList.add(task2);
		arrayTaskList.add(task2);
		arrayTaskList.add(task2);
		arrayTaskList.add(task2);
		arrayTaskList.add(task2);
		arrayTaskList.add(task2);

		System.out.println(arrayTaskList.size());
	}
}
