package com.becoder.dto;

import com.becoder.dto.NotesDto.CategoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotesRequest {

	private String title;

	private String description;

	private CategoryDto category;
}
