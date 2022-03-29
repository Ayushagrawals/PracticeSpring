package com.PracticeProject.Practice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.PracticeProject.Practice.Entity.Product;
import com.PracticeProject.Practice.Entity.Users;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	
}
