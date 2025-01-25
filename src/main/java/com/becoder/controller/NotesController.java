package com.becoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.becoder.dto.FavouriteNoteDto;
import com.becoder.dto.NotesDto;
import com.becoder.dto.NotesResponse;
import com.becoder.endpoint.NotesEnpoint;
import com.becoder.entity.FileDetails;
import com.becoder.service.NotesService;
import com.becoder.util.CommonUtil;

@RestController
public class NotesController implements NotesEnpoint {

	@Autowired
	private NotesService notesService;

	@Override
	public ResponseEntity<?> saveNotes(String notes, MultipartFile file) throws Exception {
		Boolean saveNotes = notesService.saveNotes(notes, file);
		if (saveNotes) {
			return CommonUtil.createBuildResponseMessage("Notes saved success", HttpStatus.CREATED);
		}
		return CommonUtil.createErrorResponseMessage("Notes not saved", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> downloadFile(Integer id) throws Exception {
		FileDetails fileDetails = notesService.getFileDetails(id);
		byte[] data = notesService.downloadFile(fileDetails);

		HttpHeaders headers = new HttpHeaders();
		String contentType = CommonUtil.getContentType(fileDetails.getOriginalFileName());
		headers.setContentType(MediaType.parseMediaType(contentType));
		headers.setContentDispositionFormData("attachment", fileDetails.getOriginalFileName());

		return ResponseEntity.ok().headers(headers).body(data);
	}

	@Override
	public ResponseEntity<?> getAllNotes() {
		List<NotesDto> notes = notesService.getAllNotes();
		if (CollectionUtils.isEmpty(notes)) {
			return ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> searchNotes(String key, Integer pageNo, Integer pageSize) {
		NotesResponse notes = notesService.getNotesByUserSearch(pageNo, pageSize, key);
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllNotesByUser(Integer pageNo, Integer pageSize) {
		NotesResponse notes = notesService.getAllNotesByUser(pageNo, pageSize);
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteNotes(Integer id) throws Exception {
		notesService.softDeleteNotes(id);
		return CommonUtil.createBuildResponseMessage("Delete Success", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> restoreNotes(Integer id) throws Exception {
		notesService.restoreNotes(id);
		return CommonUtil.createBuildResponseMessage("Notes restore Success", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getUserRecycleBinNotes() throws Exception {
		List<NotesDto> notes = notesService.getUserRecycleBinNotes();
		if (CollectionUtils.isEmpty(notes)) {
			return CommonUtil.createBuildResponseMessage("Notes not avaible in Recycle Bin", HttpStatus.OK);
		}
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> hardDeleteNotes(Integer id) throws Exception {
		notesService.hardDeleteNotes(id);
		return CommonUtil.createBuildResponseMessage("Delete Success", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> emptyUserRecyleBin() throws Exception {
		notesService.emptyRecycleBin();
		return CommonUtil.createBuildResponseMessage("Delete Success", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> favoriteNote(@PathVariable Integer noteId) throws Exception {
		notesService.favoriteNotes(noteId);
		return CommonUtil.createBuildResponseMessage("Notes added Favorite", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> unFavoriteNote(@PathVariable Integer favNotId) throws Exception {
		notesService.unFavoriteNotes(favNotId);
		return CommonUtil.createBuildResponseMessage("Remove Favorite", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getUserfavoriteNote() throws Exception {

		List<FavouriteNoteDto> userFavoriteNotes = notesService.getUserFavoriteNotes();
		if (CollectionUtils.isEmpty(userFavoriteNotes)) {
			return ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(userFavoriteNotes, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> copyNotes(@PathVariable Integer id) throws Exception {
		Boolean copyNotes = notesService.copyNotes(id);
		if (copyNotes) {
			return CommonUtil.createBuildResponseMessage("Copied success", HttpStatus.CREATED);
		}
		return CommonUtil.createErrorResponseMessage("Copy failed ! Try Again", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
