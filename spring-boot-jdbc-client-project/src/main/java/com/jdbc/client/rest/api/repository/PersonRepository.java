package com.jdbc.client.rest.api.repository;

import com.jdbc.client.rest.api.exception.ResourceNotFoundException;
import com.jdbc.client.rest.api.model.Person;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonRepository {

	private final JdbcClient jdbcClient;

	public PersonRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	public int insertPerson(Person person) {
		String sql = "INSERT INTO persons (name, email, city) VALUES (:name, :email, :city)";
		return jdbcClient.sql(sql).param("name", person.name()).param("email", person.email()).param("city", person.city()).update();
	}

	public List<Person> findAll() {
		String sql = "select * from persons";
		return jdbcClient.sql(sql).query(Person.class).list();
	}

	public Optional<Person> findById(Long id) {
		String sql = "select * from persons where id = :id";
		return jdbcClient.sql(sql).param("id", id).query(Person.class).optional();
	}

	@Transactional
	public void delete(Long id) {
		String sql = "delete from persons where id = ?";
		int count = jdbcClient.sql(sql).param(1, id).update();
		if (count == 0) {
			throw new ResourceNotFoundException("Person not found");
		}
	}

	@Transactional
	public void update(Person person) {
		String sql = "update persons set name = ?, email = ?, city= ? where id = ?";
		int count = jdbcClient.sql(sql).param(1, person.name()).param(2, person.email()).param(3, person.city()).param(4, person.id()).update();
		if (count == 0) {
			throw new ResourceNotFoundException("Person not found");
		}
	}

}
