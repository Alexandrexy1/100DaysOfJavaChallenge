package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import entities.Person;

public class Main {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		List<Person> people = new ArrayList<>();
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("people.txt"))){
			String peopleLine = bufferedReader.readLine();
			
			while (peopleLine != null) {
				String[] person = peopleLine.split(",");	
				people.add(new Person(person[0], Integer.parseInt(person[1]), Double.parseDouble(person[2])));			
				peopleLine = bufferedReader.readLine();
			}
			
			Collections.sort(people, (p1, p2) -> Double.compare(p2.getSalary(), p1.getSalary()));
			
			double averageSalary = people.stream().map(person -> person.getSalary()).reduce(0., (x, y) -> x + y) / people.size();
			people.forEach(System.out::println);
			// Person [name=Marco Aurelio, age=33, salary=7400.00]
			// Person [name=Matheus Figueiredo, age=29, salary=5800.00]
			// Person [name=Luana Almeida, age=25, salary=3500.00]
			// Person [name=Ana Julia, age=22, salary=3100.00]
			// Person [name=Alexandre Nascimento, age=20, salary=2300.00]
			// Person [name=Gabriel Medeiros, age=18, salary=1900.00]
			// Person [name=Maria Clara, age=17, salary=1500.00]

			System.out.println("Average salary: R$ " + String.format("%.2f", averageSalary)); 
			// Average salary: R$ 3642,86
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
}


