package com.webdev.blog_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.blog_app.payloads.CatDto;
import com.webdev.blog_app.service.impl.CategoryServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

 
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryService;
	// create category
	
	@PostMapping("/")
	 public ResponseEntity<CatDto> createCategory(@Valid @RequestBody CatDto catDto) {
		 CatDto createdCategory = categoryService.createCategory(catDto);
		 return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	 }
	// get all categories
	
	@GetMapping("/")
	public ResponseEntity<List<CatDto>> getAllCategories() {
		List<CatDto> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	// get category by id
	
	@GetMapping("/{catId}")
	public ResponseEntity<CatDto> getCategoryById(@PathVariable Integer catId) {
		CatDto category = categoryService.getCategoryById(catId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	// update category
	
	@PutMapping("/{catId}")
	public ResponseEntity<CatDto> updateCategory(@Valid @RequestBody CatDto catDto,@PathVariable Integer catId) {
		CatDto updatedCategory = categoryService.updateCategory(catDto, catId);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	// delete category
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer catId) {
		String message = categoryService.deleteCategory(catId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
