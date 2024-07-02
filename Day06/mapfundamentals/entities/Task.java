package entities;

import java.util.Objects;

public class Task  {
	private String name;
	private String task;
	
	public Task(String name, String task) {
		this.name = name;
		this.task = task;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, task);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(name, other.name) && Objects.equals(task, other.task);
	}

	@Override
	public String toString() {
		return name + ": " + task;
	}
}
