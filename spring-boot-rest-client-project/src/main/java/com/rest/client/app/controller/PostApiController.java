package com.rest.client.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.client.app.models.Post;
import com.rest.client.app.services.JsonPlaceholderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostApiController {

	private final JsonPlaceholderService postService;

	@GetMapping("")
	List<Post> findAll() {
		return postService.findAll();
	}

	@GetMapping("/{id}")
	Post findById(@PathVariable Integer id) {
		return postService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Post create(@RequestBody Post post) {
		return postService.create(post);
	}

	@PutMapping("/{id}")
	Post update(@PathVariable Integer id, @RequestBody Post post) {
		return postService.update(id, post);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id) {
		postService.delete(id);
	}

}