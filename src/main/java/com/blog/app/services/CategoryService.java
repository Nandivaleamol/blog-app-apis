package com.blog.app.services;

import java.util.List;

import com.blog.app.payloads.CategoryDto;

public interface CategoryService {
	
	// create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	void delete(Integer categoryId);
	
	//get single
	CategoryDto getCategory(Integer categoryId);
	
	// get all
	List<CategoryDto> getCategories();
}
