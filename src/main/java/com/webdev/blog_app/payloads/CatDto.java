package com.webdev.blog_app.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CatDto {

	private int catId;
	@NotBlank(message = "Category name cannot be blank")
	@Size(min = 4, message = "Category name must be at least 4 characters long")
	private String catName;
	
	@NotBlank(message = "Category description cannot be blank")
	@Size(min = 10, message = "Category description must be at least 10 characters long")
	private String catDescription;
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatDescription() {
		return catDescription;
	}
	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}
	public CatDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CatDto(int catId, String catName, String catDescription) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.catDescription = catDescription;
	}
}
