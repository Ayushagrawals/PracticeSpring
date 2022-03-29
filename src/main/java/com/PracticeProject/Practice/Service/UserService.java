package com.PracticeProject.Practice.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PracticeProject.Practice.Controller.UserController;
import com.PracticeProject.Practice.Entity.Product;
import com.PracticeProject.Practice.Entity.Users;
import com.PracticeProject.Practice.Repository.ProductRepository;
import com.PracticeProject.Practice.Repository.UserRepository;
import com.PracticeProject.Practice.exception.CustomException;
import com.PracticeProject.Practice.exception.CustomTechnicalException;
import com.google.gson.Gson;

@Service
public class UserService {
	Logger log=(Logger) LoggerFactory.getLogger(UserService.class);

UserRepository userRepo;
@Autowired
ProductRepository prodRepo;
@Autowired
public UserService(UserRepository userRepo)
{
	this.userRepo=userRepo;
}
public UserService()
{
	
}

public Users save(Users user) throws CustomException
{
	try {
		log.info("called");
		log.info("prodRepo "+prodRepo);
		List<Product> prod=prodRepo.saveAll(user.getProducts());
		Users users;
		if(prod==null)
		{
			throw new CustomException();
		}
		
		else {
			users=userRepo.save(user);
		}
		return user;
	//log.info("user "+user.getProduct().toString());
	//Map map = Gson.fromJson(user, Map.class);
	
	}
	catch(Exception e)
	{
		log.error("Exception occoured while saving in Database ");
		log.error(e+"");
		throw new CustomException();
	}
	//return user;
}

public void delete(Users user)
{
	try {	
	
	//log.info("user "+user.getProduct().toString());
	//Map map = Gson.fromJson(user, Map.class);
	//prodRepo.saveAll(user.getProducts());
	prodRepo.deleteAll(user.getProducts());
	userRepo.delete(user);
	log.info("Done delete");
	}
	catch(Exception e)
	{
		log.error("Exception occoured while deleting from Database ");
		log.error(e+"");
	}
}

public void authenticate(Users user)
{
	try {
		userRepo.save(user);
	//log.info("user "+user.getProduct().toString());
	//Map map = Gson.fromJson(user, Map.class);
	prodRepo.saveAll(user.getProducts());
	}
	catch(Exception e)
	{
		log.error("Exception occoured while saving in Database ");
		log.error(e+"");
	}
}

public void saveAll(List<Users> users)
{
	try {
		
		userRepo.saveAll(users);
		//userRepo.save(user);
	//log.info("user "+user.getProduct().toString());
	//Map map = Gson.fromJson(user, Map.class);
		//List<Product> prod=users.get(0)
	prodRepo.saveAll(users.get(0).getProducts());
	log.info("Done");
	}
	catch(CustomTechnicalException e)
	{
		log.error("Exception occoured while saving in Database ");
		log.error(e+"");
		throw new CustomTechnicalException();
		
	}
}

public  Users findAll(String userName) throws CustomException
{
	
	//Optional<User> user=userRepo.findByAge();
	//return user;
	//log.info("user "+user.getProduct().toString());
	//Map map = Gson.fromJson(user, Map.class);
	//prodRepo.saveAll(user.getProducts());
		log.info("Username in Service "+userName);
		if(userName==null)
		throw new CustomException();
		try {
		Users user=userRepo.findByUsername(userName);
		log.info("User in Service "+user);
		return user;
	}
	catch(Exception e)
	{
		log.error("Exception occoured while retrieving from Database ");
		log.error(e+"");
		throw new CustomTechnicalException();
	}
	
}

public void updateRole(String username,String password,String role)
{
	try {
		if(username==null || password==null || role==null)
		{
			throw new CustomException();
		}
	userRepo.updateUserCustom(username, password, role);
	}
	catch(Exception e)
	{
		log.error("Error occured while updating in DB "+e);
		throw new CustomTechnicalException();
	}
	

}

}
