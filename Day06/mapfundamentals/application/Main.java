package application;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import entities.Task;

public class Main {
	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.of("UTC"));
		Map<Task, Instant> tasks = new HashMap<>();
		
		tasks.put(new Task("Task1", "Studying Java for backend"), Instant.parse("2024-07-02T06:10:00Z"));
		tasks.put(new Task("Task2", "Studying Typescript for frontend"), Instant.parse("2024-07-02T15:30:00Z"));
		tasks.put(new Task("Task3", "Workout calisthenic and bodybuilding"), Instant.parse("2024-07-02T18:15:00Z"));
		tasks.put(new Task("Task4", "Something else"), Instant.parse("2024-07-02T06:10:00Z"));
		
		Task taskToRemove = new Task("Task4", "Something else");
		tasks.remove(taskToRemove);
		
		for (Task task: tasks.keySet()) {
			System.out.println(task + " at  " + dtf.format(tasks.get(task)));
			// Task1: Studying Java for backend at  02/07/2024 06:10:00
			// Task3: Workout calisthenic and bodybuilding at  02/07/2024 18:15:00
			// Task2: Studying Typescript for frontend at  02/07/2024 15:30:00
		}
	}
}


