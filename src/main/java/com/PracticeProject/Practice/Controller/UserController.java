package com.PracticeProject.Practice.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PracticeProject.Practice.Entity.Users;
import com.PracticeProject.Practice.Service.UserService;
import com.PracticeProject.Practice.advice.GlobalExceptionAdvice;
import com.PracticeProject.Practice.exception.AlreadyExistException;
import com.PracticeProject.Practice.exception.CustomException;
import com.PracticeProject.Practice.exception.UserNotExists;
import com.PracticeProject.Practice.util.JwtUtil;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/user")
public class UserController {
	Logger log=(Logger) LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	GlobalExceptionAdvice globalExcHandler;
	
	@RequestMapping("/save")
	public ResponseEntity<?> save(@RequestBody Users user) throws CustomException
	{
		//logg
		//log.info("hi ",user);
			
		Users users;
		users=userService.findAll(user.getUserName());
		if(users!=null)
		{
			throw new AlreadyExistException();
		}
		else
		{
			try {
		users=userService.save(user);
			}
		catch(Exception e)
		{
			return new ResponseEntity<Exception>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(users==null)
		{
			//return users;
			CustomException ce=new CustomException();
			//return new ResponseEntity<CustomException>(ce,"Error in saving into DB");
			throw new CustomException();
		}
		else {
			return new ResponseEntity<Users>(users,HttpStatus.OK);
		}
		}
		}
		
	
	@RequestMapping("/authenticate")
	public String authenticate(@RequestBody Users user) throws Exception
	{
		//logg
		//log.info("hi ",user);
		//try {
			log.info("Username "+user.getUserName());
			log.info("Password "+user.getPassword());
			if(user==null ||  user.getPassword()==null || user.getUsername()==null)
			{
				throw new CustomException();
			}
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
		//}
		//catch(Exception e)
		//{
			//log.info(e.toString());
			//throw new Exception("invalid username/password");
			
		//}
		
		
		return jwtUtil.generateToken(user.getUserName());
		//userService.save(user);
	}
	
	@GetMapping("/findAll")
	//@ExceptionHandler(value={CustomException.class})
	public ResponseEntity<?> findAll(@RequestParam String userName) throws CustomException
	{	
		//try {
		Users user=userService.findAll(userName);
		
		if(user==null)
		{
			throw new UserNotExists();
		}
		else {
			return new ResponseEntity<Users>(user,HttpStatus.OK);
		}
		//}
		//catch (CustomException e){
			//return e;
			//throw new CustomException(e.getErrorCode(),e.getErrorMessage());
			//return new ResponseEntity<CustomException>(ce,HttpStatus.NO_CONTENT);
			//S
		//}
		//return "hello";
	}
	@DeleteMapping("/delete")
	public void delete(@RequestBody Users user)
	{
		//logg
		//log.info("hi ",user);
		
		 userService.delete(user);
	}
	
	@PutMapping("/updateRole")
	public void updateRole(@RequestBody Map<String,String> userDetailsMap) throws Exception
	{
		//logg
		//log.info("hi ",user);
		String username=userDetailsMap.get("username");
		String password=userDetailsMap.get("password");
		String role=userDetailsMap.get("role");
		Users user=userService.findAll(username);
		log.info(user +" user value");
		if(user==null)
		{
			throw new Exception("User with "+username+" not present in DB");
		}
		else {
			try {
			userService.updateRole(username,password,role);
			}
			catch(Exception e)
			{
				log.error("Error occured while updating in DB "+e);
			}
			//return users;
		}
		//userService.delete(user);
	}
	
}
