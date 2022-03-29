package com.PracticeProject.Practice.util;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.PracticeProject.Practice.Repository.UserRepository;
import com.PracticeProject.Practice.Service.UserService;

//@ExtendWith(MyUtil.class)
class MyUtilTest {
	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserService userService;
	MyUtil mock=Mockito.mock(MyUtil.class);
@Test
public void verifyTest()
{
	
	mock.hello(10,20);
	verify(mock).hello(10,20);
	verify(mock,times(1)).hello(10,20);
}

@Test
public void verifyTest2()
{
	
	List mocker=Mockito.mock(List.class);
	mocker.add(10);
	ArgumentCaptor argcaptor=ArgumentCaptor.forClass(Integer.class);
	verify(mocker).add(argcaptor.capture());
	
}

@Test
public void verifyTest3()
{
	
	List mocker=Mockito.mock(List.class);
	mocker.add(10);
	ArgumentCaptor argcaptor=ArgumentCaptor.forClass(Integer.class);
	verify(mocker).add(argcaptor.capture());
	
}

@Test
public void verifyTest4()
{
	
	doNothing().when(mock).hi();
	mock.hi();
	verify(mock,times(1)).hi();
	
}


	

}
