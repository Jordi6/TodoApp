package com.example.ToDoApp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToDoApp.pojo.Todos;
import com.example.ToDoApp.pojo.User;
import com.example.ToDoApp.repo.TodoRepository;
import com.example.ToDoApp.repo.UserRepository;


@RestController
public class TodoController {
	private TodoRepository repo;
	private UserRepository userRepo;

	
	@Autowired
	public TodoController(TodoRepository repo, UserRepository userRepo) {
		this.repo = repo;
		this.userRepo = userRepo;
	}
	
	@RequestMapping("/get-todos")
	public List<Todos> getTodos() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userRepo.findFirstByName(name);
		
		List<Todos> todos = (List<Todos>) repo.findAll();
		return todos;

	}
	
	@RequestMapping("/save-todos")
	public Todos saveTodos(@RequestParam String content, @RequestParam int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepo.findFirstByName(name);
        if(id > 0) {
        	Todos todos = repo.findById(id).get();
        	if(user.getName().equals(todos.getUser().getName())) {
        		todos.setContent(content);
        		todos.setDate(new Date());
        		todos.setCompleted(false);
        		todos = repo.save(todos);
        		return todos;
        	}
        }else {
        	Todos todos = new Todos();
            todos.setContent(content);
            todos.setCompleted(false);
            todos.setDate(new Date());
            todos.setUser(user);
            
            todos = repo.save(todos);
            return todos;
        }
        
		return null;
	}
	
	
	
	//real method for deleting todos from repo and db
	@RequestMapping("/delete-todos")
	public Todos deleteTodos(@RequestParam int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userRepo.findFirstByName(name);
		Todos todos = repo.findById(id).get();
		if (user.getName().equals(todos.getUser().getName())) {
			repo.delete(todos);
		}
		
		return todos;
	}
	
	
	
}


