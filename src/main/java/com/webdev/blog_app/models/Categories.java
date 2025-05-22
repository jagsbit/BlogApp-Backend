package com.webdev.blog_app.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int catId;
	private String catName;
	private String catDescription;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Posts> posts=new ArrayList<Posts>();
 
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
	
	
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categories(int catId, String catName, String catDescription) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.catDescription = catDescription;
	}
	 
}
