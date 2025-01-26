package com.becoder.endpoint;

import static com.becoder.util.Constants.DEFAULT_PAGE_NO;
import static com.becoder.util.Constants.DEFAULT_PAGE_SIZE;
import static com.becoder.util.Constants.ROLE_ADMIN;
import static com.becoder.util.Constants.ROLE_ADMIN_USER;
import static com.becoder.util.Constants.ROLE_USER;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.becoder.dto.NotesDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "NotesController", description = "Notes Releated all the operation")
@RequestMapping("/api/v1/notes")
public interface NotesEnpoint {

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@PostMapping(value = "/", consumes = "multipart/form-data")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> saveNotes(
			@RequestParam @Parameter(
					    description = "JSON string representing the notes",
			            required = true,
			            content = @Content(schema = @Schema(implementation = NotesDto.class))) 
			            String notes,
			@RequestParam(required = false) MultipartFile file) throws Exception;

	@Operation(summary = "Download File", description = "User Saved Notes After Login")
	@GetMapping("/download/{id}")
	@PreAuthorize(ROLE_ADMIN_USER)
	public ResponseEntity<?> downloadFile(@PathVariable Integer id) throws Exception;

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@GetMapping("/")
	@PreAuthorize(ROLE_ADMIN)
	public ResponseEntity<?> getAllNotes();

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@GetMapping("/search")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> searchNotes(@RequestParam(name = "key", defaultValue = "") String key,
			@RequestParam(name = "pageNo", defaultValue = DEFAULT_PAGE_NO) Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize);

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@GetMapping("/user-notes")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getAllNotesByUser(
			@RequestParam(name = "pageNo", defaultValue = DEFAULT_PAGE_NO) Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize);

	@Operation(summary = "Delete Notes", description = "User Saved Notes After Login")
	@GetMapping("/delete/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> deleteNotes(@PathVariable Integer id) throws Exception;

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@GetMapping("/restore/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> restoreNotes(@PathVariable Integer id) throws Exception;

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@GetMapping("/recycle-bin")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getUserRecycleBinNotes() throws Exception;

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@DeleteMapping("/delete/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> hardDeleteNotes(@PathVariable Integer id) throws Exception;

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@DeleteMapping("/delete")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> emptyUserRecyleBin() throws Exception;

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@GetMapping("/fav/{noteId}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> favoriteNote(@PathVariable Integer noteId) throws Exception;

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@DeleteMapping("/un-fav/{favNotId}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> unFavoriteNote(@PathVariable Integer favNotId) throws Exception;

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@GetMapping("/fav-note")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getUserfavoriteNote() throws Exception;

	@Operation(summary = "Save Notes", description = "User Saved Notes After Login")
	@GetMapping("/copy/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> copyNotes(@PathVariable Integer id) throws Exception;
}
