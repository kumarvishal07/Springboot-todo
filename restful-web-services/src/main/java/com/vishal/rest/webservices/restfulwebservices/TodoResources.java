package com.vishal.rest.webservices.restfulwebservices;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
public class TodoResources {

	@Autowired
	private HardCodeService todoService;

	@GetMapping(path = "/helloworld/hello")
	public String getHello() {
		return "hello world";
	}

	@GetMapping(path = "/users/{username}/todo")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoService.findAll();
	}

	@GetMapping(path = "/users/{username}/todo/{id}")
	public Todo getTodoById(@PathVariable String username, @PathVariable long id) {
		return todoService.findById(id);
	}

	@DeleteMapping("/delete/{username}/todo/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String username, @PathVariable Long id) {
		Todo todo = todoService.deletedById(id);
		if (todo != null) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/users/{username}/todo/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable Long id,
			@RequestBody Todo todo) {
		return new ResponseEntity<Todo>(todoService.save(todo), HttpStatus.OK);

	}

	@PostMapping("/users/{username}/todo")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo cretedTodo = todoService.save(todo);
		URI uri;
		uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cretedTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
