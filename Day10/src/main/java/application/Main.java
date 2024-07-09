package application;

import database.JpaRepository;
import entities.Passport;
import entities.Person;
import util.EntityManagerUtil;

public class Main {
	public static void main(String[] args) {
        JpaRepository<Person> repositoryPerson = new JpaRepository<>(Person.class, "people");
        
        Person person = new Person("Joaquim", 43);
        Passport passport = new Passport("123242356690");
        person.setPassport(passport);
        passport.setPerson(person);
        repositoryPerson.create(person);
        
        EntityManagerUtil.close();
        
	}
}
