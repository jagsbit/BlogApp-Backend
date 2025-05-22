package com.webdev.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webdev.blog_app.models.Categories;

public interface CategoryRepo extends JpaRepository<Categories, Integer> {

}
