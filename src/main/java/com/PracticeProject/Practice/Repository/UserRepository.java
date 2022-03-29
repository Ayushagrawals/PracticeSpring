package com.PracticeProject.Practice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.PracticeProject.Practice.Entity.Users;

public interface UserRepository extends JpaRepository<Users,Integer>{
	//@Query("select * from User s where age=")
	//Optional<User> findByAge();
	Users findByUsername(String username);
	@Transactional
	@Modifying
	@Query(value="update User set role=:role where username=:username and password=:password",nativeQuery=true)
	void updateUserCustom(String username,String password,String role);
	//Users deleteByUsername(String username);
	

}
