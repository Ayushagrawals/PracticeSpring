package com.PracticeProject.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.PracticeProject.Practice.Entity.Product;
import com.PracticeProject.Practice.Entity.Users;
import com.PracticeProject.Practice.Service.UserService;

@SpringBootApplication
@PropertySource("secret.properties")
public class PracticeApplication {
	@Autowired
	UserService userService;
	@PostConstruct
	public void initUsers()
	{
		List<Product> proList=new ArrayList<Product>();
		Product produ=new Product(200,"Mob",5,10);
		proList.add(produ);
		List<Users> users=Stream.of(new Users(101,"Ayush","121234","ROLE_ADMIN",24,23.0,proList)).collect(Collectors.toList());
		userService.saveAll(users);
	}
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

}
