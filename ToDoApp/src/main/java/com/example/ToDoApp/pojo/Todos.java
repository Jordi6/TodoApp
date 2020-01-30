package com.example.ToDoApp.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="todos")
public class Todos {
	@Id
	@Column(name="todos_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="todos_user_id", nullable=false)
	private User user;
	
	@Column(name="todos_date")
	private Date date;
	
	@Column(name="todos_content")
	private String content;
	
	@Column(name="todo_completed")
	private boolean completed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
