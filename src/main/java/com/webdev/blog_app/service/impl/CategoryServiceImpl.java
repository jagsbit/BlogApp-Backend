package com.webdev.blog_app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.blog_app.execptions.ResourceNotFoundException;
import com.webdev.blog_app.models.Categories;
import com.webdev.blog_app.payloads.CatDto;
import com.webdev.blog_app.repository.CategoryRepo;
import com.webdev.blog_app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private  CategoryRepo categoryRepo;
	
	@Autowired
    private ModelMapper modelMapper;

	
	// add
	@Override
	public CatDto createCategory(CatDto catDto) {
		// TODO Auto-generated method stub
		Categories category = modelMapper.map(catDto, Categories.class);
		Categories savedCategory = categoryRepo.save(category);
		return modelMapper.map(savedCategory, CatDto.class);
		 
	}
	
	// get all

	@Override
	public List<CatDto> getAllCategories() {
		// TODO Auto-generated method stub
		
		List<Categories> categories = categoryRepo.findAll();
		
		List<CatDto> catDtos = new ArrayList<>();
		for (Categories category : categories) {
			CatDto catDto = modelMapper.map(category, CatDto.class);
			catDtos.add(catDto);
		}
		return catDtos;
	}

	// get by id
	@Override
	public CatDto getCategoryById(Integer catId) {
		// TODO Auto-generated method stub
		Categories category = categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category","catId", catId));
		return modelMapper.map(category, CatDto.class);
	}

	
	// update
	@Override
	public CatDto updateCategory(CatDto catDto, Integer catId) {
		// TODO Auto-generated method stub
		Categories category = categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category","catId", catId));
		category.setCatName(catDto.getCatName());
		category.setCatDescription(catDto.getCatDescription());
		Categories updatedCategory = categoryRepo.save(category);
		return modelMapper.map(updatedCategory, CatDto.class);
	}

	@Override
	public String deleteCategory(Integer catId) {
		// TODO Auto-generated method stub
		Categories category = categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category","catId", catId));
		categoryRepo.delete(category);
		return "Category deleted successfully";
	}

}
