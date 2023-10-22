package com.fooddelivery.services;






import java.util.List;

import com.fooddelivery.entity.Category;
import com.fooddelivery.entity.Food;
import com.fooddelivery.entity.FoodPaging;

public interface FoodService {

	Food addFoodItem(Food food);
	List<Food> getAllFoodItems(int pageNo, int pageSize, String sortBy, String sortDir);
	
	List<Food> getFoodByCategory(Category category,int pageNo, int pageSize, String sortBy, String sortDir);
	Food getFoodById(long foodId);
	void deleteFoodById(long foodId);
	Food updateFood(Food food, long foodId);
	List<Food> getFoods();
	List<Food> searchFood(String query);
	FoodPaging findByCategory(Category c, int pageNo, int pageSize);
	FoodPaging getAllFoods(int pageNo, int pageSize);
	Food getFoodByFoodId(long foodId);

}
