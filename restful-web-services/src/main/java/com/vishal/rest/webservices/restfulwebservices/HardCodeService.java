package com.vishal.rest.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HardCodeService {

	private static List<Todo> todos = new ArrayList<>();
	private static int idCounter = 0;

	static {
		todos.add(new Todo(++idCounter, "vishal", "marry to you this year", new Date(), false));
		todos.add(new Todo(++idCounter, "drishti", "marry to you this year", new Date(), false));
		todos.add(new Todo(++idCounter, "vishal", "Buy a home", new Date(), false));
		todos.add(new Todo(++idCounter, "drishti", "marry to you this year", new Date(), false));
		todos.add(new Todo(++idCounter, "vishal", "Visit all over world", new Date(), false));
	}

	public Todo save(Todo todo) {
		if(todo.getId() == -1) {
			todo.setId(++idCounter);
			todos.add(todo);
		}
		else {
			deletedById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	public List<Todo> findAll() {
		return todos;
	}

	public Todo deletedById(Long id) {
		Todo todo = findById(id);
		if (todo == null)
			return null;
		todos.remove(todo);
		return todo;

	}

	public Todo findById(Long id) {
		for (Todo t : todos) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}

	
}
