package com.webdev.blog_app.service;

import java.util.List;

import com.webdev.blog_app.payloads.CatDto;

public interface CategoryService {

	//add
	CatDto createCategory(CatDto catDto);
	
	// get all
	List<CatDto> getAllCategories();
	
	// get by id
	CatDto getCategoryById(Integer catId);
	
	// update 
	
	CatDto updateCategory(CatDto catDto, Integer catId);
	
	// delete
	String deleteCategory(Integer catId);
}
