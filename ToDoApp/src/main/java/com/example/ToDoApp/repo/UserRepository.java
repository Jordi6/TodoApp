package com.example.ToDoApp.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.ToDoApp.pojo.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	public User findFirstByName(String name);

}
