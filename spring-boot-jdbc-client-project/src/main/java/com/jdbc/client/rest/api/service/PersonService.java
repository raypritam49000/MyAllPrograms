package com.jdbc.client.rest.api.service;

import java.util.List;

import com.jdbc.client.rest.api.model.Person;

public interface PersonService {
	public void createPerson(Person person);
	public Person getPersonById(Long id);
	public List<Person> getPersons(); 
	public void deletePerson(Long id); 
	public void updatePerson(Person person);	
}
