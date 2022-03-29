package com.PracticeProject.Practice.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

//import org.springframework.test.context.junit5.MockitoExtension;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PracticeProject.Practice.Entity.Product;
import com.PracticeProject.Practice.Entity.Users;
import com.PracticeProject.Practice.Repository.UserRepository;
import com.PracticeProject.Practice.exception.CustomException;

@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
//@PropertySource("/secret.properties")
public class UserServiceTest {
	Logger log=(Logger) LoggerFactory.getLogger(UserService.class);
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	UserService s=new UserService(userRepository);
	
	@BeforeEach
	public void setup() {
	    MockitoAnnotations.initMocks(UserServiceTest.class);
	}
	
	@Test
	public void test()
	{
		assertEquals("1","1");
	}
	
	@Test
	public void testByUsername() throws CustomException {
		//UserService us = new UserService();
		//UserRepository userRepository=new UserRepository();
		Users user=new Users();
		//Optional<UserRepository> findById = Optional.of(se);
		
		when(userRepository.findByUsername("Ayush")).thenReturn(user);
		try {
			assertNotNull(userService.findAll("Ayush"));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @Disabled
	 * 
	 * @Test(expected=CustomException.class) public void testExceptionFind() throws
	 * CustomException { //UserRepository u=new UserRepository();
	 * 
	 * 
	 * }
	 */
	
	@Test
	public void testBySave()  {
		//UserService us = new UserService();
		//UserRepository userRepository=new UserRepository();
		Users user=new Users();
		user.setuserName("Ayush");
		user.setPassword("234512");
		user.setAge(24);
		user.setRole("ROLE_ADMIN");
		Product prod=new Product();
		prod.setPrice(123);
		prod.setProductName("Mobile");
		prod.setQty(123);
		List<Product> prodList=Stream.of(prod).collect(Collectors.toList());
		user.setProducts(prodList);
		
		//Optional<UserRepository> findById = Optional.of(se);
		log.info("userRepo "+userRepository+"service "+s);
		when(userRepository.save(user)).thenReturn(user);
		try {
			assertNotNull(userService.save(user));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	@Test(expected=CustomException.class)
	public void testSaveException() throws CustomException {
		
		//userService.findAll(null);
		log.info(userService +"userService "+s);
			s.save(null);
		
	}
	
	@Test
	void verify1Test()
	{
		UserRepository mockRepo=mock(UserRepository.class);
		//mockRepo.save(user);
		//verify(mockRepo).save
		
	}

	@Test
	void verify4test()
	{
		List mock=Mockito.mock(List.class);
		//mock.add(e);
	}
	
	
	
}
