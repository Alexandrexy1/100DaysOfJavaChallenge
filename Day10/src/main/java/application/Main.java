package application;

import database.JpaRepository;
import entities.Person;
import util.EntityManagerUtil;

public class Main {
	public static void main(String[] args) {
        JpaRepository<Person> repository = new JpaRepository<>(Person.class, "people");
      
        // repository.create(new Person("Joaquim", 43));
        // repository.create(new Person("Julia", 29));
        // repository.create(new Person("Joao", 17));

        Person personFoundById = repository.findById(8);
        System.out.println(personFoundById); // Person [id=4, name=Julia, age=29]
        
        personFoundById.setName("Ana");
        repository.update(personFoundById);
        
        personFoundById = repository.findById(personFoundById.getId());
        System.out.println(personFoundById); // Person [id=4, name=Ana, age=29]

        repository.delete(personFoundById.getId()); // successfully deleted
        
        EntityManagerUtil.close();
	}
}
