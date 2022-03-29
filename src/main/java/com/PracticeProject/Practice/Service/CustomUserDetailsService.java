package com.PracticeProject.Practice.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PracticeProject.Practice.Controller.UserController;
import com.PracticeProject.Practice.Entity.Users;

import com.PracticeProject.Practice.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	Logger log=(Logger) LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	private  UserRepository userrepository;
	@Autowired
	Optional<Users> user;
	  @Override 
	  public UserDetails loadUserByUsername(String username) throws
	  UsernameNotFoundException 
	  { 
		  // TODO Auto-generated method stub //List Users
		  
		  log.info("Method called load by username");
		  //log.info();
		  
		  
	  user=Optional.of(userrepository.findByUsername(username));
	  log.info("username "+user.get().getUsername());
	  log.info("password "+user.get().getPassword());
	  user.orElseThrow(()->new UsernameNotFoundException("Not found "+username));
	  //return new User(user.get().getUserName(),user.get().getPassword(),new ArrayList<>());
	  return  user.map(MyUserDetails::new).get();
	 

}
}
