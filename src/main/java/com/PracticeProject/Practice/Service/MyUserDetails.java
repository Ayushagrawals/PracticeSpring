 package com.PracticeProject.Practice.Service;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.PracticeProject.Practice.Entity.Users;
import com.PracticeProject.Practice.Repository.UserRepository;
@Service
public class MyUserDetails implements UserDetails{
	String userName;
	String password;
	List<GrantedAuthority> authorities;
	@Autowired
	UserRepository userRepo;
	//@Autowired
	//Users users;
	public MyUserDetails()
	{
		
	}
	public MyUserDetails(Users user)
	{
	this.userName=user.getUserName();
	this.password=user.getPassword();
	this.authorities=Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		//Users users=userRepo.findByUsername(userName);
		//if(users!=null)
		//userName=users.getUsername();
	//	else {
		//	return null;
	//	}
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
