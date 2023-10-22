package com.fooddelivery.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fooddelivery.entity.Category;
import com.fooddelivery.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{
	List<Food> findFoodByCategory(Category cId, Pageable pageable);
	@Query("SELECT f from Food f WHERE " +
			 " f.foodName LIKE CONCAT('%', :query, '%') OR " +
			 " f.description LIKE CONCAT('%', :query, '%') ")
	List<Food> searchFoods(@Param("query")String query);
	Page<Food> findByCategory(Category c, Pageable paging);

}
