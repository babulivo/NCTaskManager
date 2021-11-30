package ua.edu.sumdu.j2se.podveza.tasks;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		Task task1 = new Task("Football", 1);
		Task task2 = new Task("Football", 1);

		LinkedTaskList linkedTaskList = new LinkedTaskList();
		linkedTaskList.add(task1);
		linkedTaskList.add(task2);

		System.out.println(linkedTaskList.getTask(0).hashCode() == linkedTaskList.getTask(1).hashCode());

		System.out.println("------------------------------");

		Iterator<Task> it = linkedTaskList.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
			it.remove();
		}

		/*Task task1 = new Task("Football", 1);
		Task task2 = new Task("Swimming", 2);
		Task task3 = new Task("Playing PC", 3);
		Task task4 = new Task("Date", 4);
		Task task5 = new Task("Football", 1);

		ArrayTaskList task = new ArrayTaskList();

		System.out.println(task.size());

		task.add(task1);
		task.add(task2);
		task.add(task3);
		task.add(task4);
		task.add(task5);

		System.out.println(task.size());

		task.incoming(1, 2);*/
	}
}
