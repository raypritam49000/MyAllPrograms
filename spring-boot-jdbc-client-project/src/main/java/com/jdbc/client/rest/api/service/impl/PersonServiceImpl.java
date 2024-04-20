package com.jdbc.client.rest.api.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jdbc.client.rest.api.exception.ResourceNotFoundException;
import com.jdbc.client.rest.api.model.Person;
import com.jdbc.client.rest.api.repository.PersonRepository;
import com.jdbc.client.rest.api.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	
	private final PersonRepository personRepository;
	
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public void createPerson(Person person) {
		personRepository.insertPerson(person);	
	}

	@Override
	public Person getPersonById(Long id) {
		return personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Person not found with given id : "+id));
	}

	@Override
	public List<Person> getPersons() {
		return personRepository.findAll();
	}

	@Override
	public void deletePerson(Long id) {
		personRepository.delete(id);
	}

	@Override
	public void updatePerson(Person person) {
	   personRepository.update(person);	
	}

}
