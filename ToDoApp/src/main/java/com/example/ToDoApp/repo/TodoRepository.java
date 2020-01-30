package com.example.ToDoApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.ToDoApp.pojo.Todos;
import com.example.ToDoApp.pojo.User;

public interface TodoRepository extends PagingAndSortingRepository<Todos, Integer> {

	List<Todos> findAllByOrderByDateDesc(Pageable page);
	public List<Todos> findAll();
	public Optional<Todos> findById(Integer id);
	
}
