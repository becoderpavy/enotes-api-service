package com.becoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.dto.CategoryDto;
import com.becoder.dto.CategoryResponse;
import com.becoder.endpoint.CategoryEndpoint;
import com.becoder.service.CategoryService;
import com.becoder.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CategoryController implements CategoryEndpoint {

	@Autowired
	private CategoryService categoryService;

	@Override
	public ResponseEntity<?> saveCategory(CategoryDto categoryDto) {

		Boolean saveCategory = categoryService.saveCategory(categoryDto);
		if (saveCategory) {
			return CommonUtil.createBuildResponseMessage("saved success", HttpStatus.CREATED);
		} else {
			return CommonUtil.createErrorResponseMessage("Category Not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getAllCategory() {
		List<CategoryDto> allCategory = categoryService.getAllCategory();
		if (CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return CommonUtil.createBuildResponse(allCategory, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> getActiveCategory() {

		List<CategoryResponse> allCategory = categoryService.getActiveCategory();
		if (CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return CommonUtil.createBuildResponse(allCategory, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> getCategortDetailsById(Integer id) throws Exception {

		CategoryDto categoryDto = categoryService.getCategoryById(id);
		if (ObjectUtils.isEmpty(categoryDto)) {
			return CommonUtil.createErrorResponseMessage("Internal Server Error", HttpStatus.NOT_FOUND);
		}
		return CommonUtil.createBuildResponse(categoryDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteCategoryById(Integer id) {
		Boolean deleted = categoryService.deleteCategory(id);
		if (deleted) {
			return CommonUtil.createBuildResponse("Category deleted success", HttpStatus.OK);
		}
		return CommonUtil.createErrorResponseMessage("Category Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
