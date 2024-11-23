package com.becoder.service;

import java.util.List;

import com.becoder.dto.TodoDto;

public interface TodoService {

	public Boolean saveTodo(TodoDto todo) throws Exception;

	public TodoDto getTodoById(Integer id) throws Exception;

	public List<TodoDto> getTodoByUser();

}
