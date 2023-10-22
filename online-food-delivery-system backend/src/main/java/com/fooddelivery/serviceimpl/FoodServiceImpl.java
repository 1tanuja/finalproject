package com.fooddelivery.serviceimpl;


import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.fooddelivery.entity.Category;
import com.fooddelivery.entity.Food;
import com.fooddelivery.entity.FoodPaging;
import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.repository.FoodRepository;
import com.fooddelivery.services.FoodService;


@Service
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	private FoodRepository foodRepository;

	
	@Override
	public Food addFoodItem(Food food) {
	    return foodRepository.save(food);
	}


	@Override
	public List<Food> getAllFoodItems(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		org.springframework.data.domain.Pageable pageable=org.springframework.data.domain.PageRequest.of(pageNo, pageSize, sort);
		Page<Food> findAll = foodRepository.findAll(pageable);
		List<Food> foods = findAll.getContent();
		return foods;
	}


	@Override
	public List<Food> getFoodByCategory(Category category, int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		org.springframework.data.domain.Pageable pageable=org.springframework.data.domain.PageRequest.of(pageNo, pageSize, sort);
		return foodRepository.findFoodByCategory(category,pageable);
		
	}


	@Override
	public Food getFoodById(long foodId) {
		 Food food = foodRepository.findById(foodId).orElseThrow(()-> new ResourceNotFoundException("food ", "foodId", foodId));
		return food;
	}


	@Override
	public void deleteFoodById(long foodId) {
		foodRepository.findById(foodId).orElseThrow(()-> new ResourceNotFoundException("food ", "foodId", foodId));
		foodRepository.deleteById(foodId);
		
	}


	@Override
	public Food updateFood(Food food, long foodId) {
		Food newfood = foodRepository.findById(foodId).orElseThrow(()->new ResourceNotFoundException("food ", "foodId", foodId));
		newfood.setFoodName(food.getFoodName());
		newfood.setDescription(food.getDescription());
		newfood.setPrice(food.getPrice());
		newfood.setCategory(food.getCategory());
		newfood.setImageUrl(food.getImageUrl());
		return foodRepository.save(newfood);
		
	}


	@Override
	public List<Food> getFoods() {
		return foodRepository.findAll();
		
	}


	@Override
	public List<Food> searchFood(String query) {
		List<Food> food = foodRepository.searchFoods(query);
		return food;
	}


	@Override
	public FoodPaging findByCategory(Category c, int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Food> pageResult = foodRepository.findByCategory(c, paging);
		FoodPaging food = new FoodPaging();
		food.setTotalFood(pageResult.getTotalElements());
		if(pageResult.hasContent()) {
            food.setFood(pageResult.getContent());
        } else {
        	 food.setFood(new ArrayList<Food>());
        }
		return food;
		
	}


	@Override
	public FoodPaging getAllFoods(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Food> pageResult = foodRepository.findAll(paging);
		FoodPaging pr = new FoodPaging();
		pr.setTotalFood(pageResult.getTotalElements());
		System.out.println(">>>>>"+ pageResult.getTotalPages());
		if(pageResult.hasContent()) {
            pr.setFood(pageResult.getContent());
        } else {
        	 pr.setFood(new ArrayList<Food>());
        }
		return pr;
	}


	@Override
	public Food getFoodByFoodId(long foodId) {
		// TODO Auto-generated method stub
		return null;
	}


	

	
	
	

}
