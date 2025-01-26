package com.becoder.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.becoder.dto.TodoDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Todo", description = "All the Todo Operation APIs")
@RequestMapping("/api/v1/todo")
public interface TodoEndpoint {

	@Operation(summary = "Save Todo", tags = { "Notes" }, description = "Save Todo")
	@PostMapping("/")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> saveTodo(@RequestBody TodoDto todo) throws Exception;

	@Operation(summary = "Get Todo", tags = { "Notes" }, description = "Get Todo")
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getTodoById(@PathVariable Integer id) throws Exception;

	@Operation(summary = "Get All Todo By User", tags = { "Notes" }, description = "Get All Todo By User")
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getAllTodoByUser() throws Exception;

}
