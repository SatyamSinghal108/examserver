package com.exam.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.exams.Category;
import com.exam.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return this.categoryRepository.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(categoryRepository.findAll());
	}

	@Override
	public Category getCategory(Long CategoryID) {
		// TODO Auto-generated method stub
		return this.categoryRepository.findById(CategoryID).get();
	}

	@Override
	public void deleteCategory(Long CategoryID) {
		// TODO Auto-generated method stub
		Category category = new Category();
		category.setCid(CategoryID);
		this.categoryRepository.delete(category);
		 
	}

	

}
