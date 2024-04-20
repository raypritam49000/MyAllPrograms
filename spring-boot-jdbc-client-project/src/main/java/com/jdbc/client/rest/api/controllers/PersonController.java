package com.jdbc.client.rest.api.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.client.rest.api.model.Person;
import com.jdbc.client.rest.api.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

	private final PersonService presonService;

	public PersonController(PersonService presonService) {
		this.presonService = presonService;
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createPerson(@RequestBody Person person) {
		presonService.createPerson(person);
		Map<String, Object> responseMap = Map.of("message", "Person has been created", "statusCode", 201, "status",
				"Created", "success", true);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Person> getPersons(){
		return presonService.getPersons();
	}
	
	@GetMapping("/{id}")
	public Person getPerson(@PathVariable("id") Long id) {
		return presonService.getPersonById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable("id") Long id) {
		presonService.deletePerson(id);
		Map<String, Object> responseMap = Map.of("message", "Person has been deleted", "statusCode", 200, "status",
				"Success", "success", true);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Map<String, Object>> updatePerson(@RequestBody Person person) {
		presonService.updatePerson(person);
		Map<String, Object> responseMap = Map.of("message", "Person has been updated", "statusCode", 200, "status",
				"Updated", "success", true);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

}
