package com.becoder.dto;

import com.becoder.entity.Notes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouriteNoteDto {
	
	private Integer id;

	private NotesDto note;

	private Integer userId;
}
