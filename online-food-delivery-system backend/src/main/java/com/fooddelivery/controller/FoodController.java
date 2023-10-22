package com.fooddelivery.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.fooddelivery.entity.Category;
import com.fooddelivery.entity.Food;
import com.fooddelivery.entity.FoodPaging;
import com.fooddelivery.services.FoodService;


import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	
	@PostMapping("/addFood")
	public ResponseEntity<Food> addFoodItem(@Valid @RequestBody Food food){
		Food addFoodItem = foodService.addFoodItem(food);
		return new ResponseEntity<>(addFoodItem,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllFood")
	public List<Food> getAllFoodItems(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = "foodId",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
		 List<Food> allFoodItems = foodService.getAllFoodItems(pageNo,pageSize,sortBy,sortDir);
		 return allFoodItems;
	}
	
	@GetMapping("/getFoodByCategory/{categoryId}")
	public List<Food> getFoodByCategory(@PathVariable("categoryId")int categoryId, @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = "foodId",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
		Category cId = Category.valueOf(categoryId);
		return foodService.getFoodByCategory(cId,pageNo,pageSize,sortBy,sortDir);
			
	}
	
	@GetMapping("/getFoodById/{foodId}")
	public ResponseEntity<Food> getFoodById(@PathVariable("foodId")long foodId){
		Food foodById = foodService.getFoodById(foodId);
		return new ResponseEntity<>(foodById,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/delete/{foodId}")
	public ResponseEntity<String> deleteFoodById(@PathVariable("foodId")long foodId){
		foodService.deleteFoodById(foodId);
		return new ResponseEntity<>("Data deleted Successfully",HttpStatus.OK);	
	}
	
	@PutMapping("/update/{foodId}")
	public ResponseEntity<Food> updateFood(@RequestBody Food food,@PathVariable("foodId") long foodId){
		Food updateFood = foodService.updateFood(food,foodId);
		return new ResponseEntity<>(updateFood,HttpStatus.CREATED);
	}
	
	@GetMapping("/getFoods")
	public List<Food> getFoods(){
		return foodService.getFoods();
		
	}
	
	@GetMapping("/search/{query}")
	public List<Food> searchFood(@PathVariable("query") String query){
	 List<Food> searchFood = foodService.searchFood(query);
	 return searchFood;
	}
	
	@GetMapping("/{categoryId}/{pageNo}/{pageSize}")
	public FoodPaging getAllProductsByCategory(@PathVariable("categoryId") int categoryId, @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
		Category c = Category.valueOf(categoryId);
		return foodService.findByCategory(c, pageNo, pageSize);
	}
	
	@GetMapping("/{pageNo}/{pageSize}")
	public FoodPaging getAllProducts(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
		return foodService.getAllFoods(pageNo, pageSize);
	}
	
	
	
	
	
}


