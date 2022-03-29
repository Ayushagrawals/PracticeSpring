package com.PracticeProject.Practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.PracticeProject.Practice.Service.CustomUserDetailsService;
import com.PracticeProject.Practice.filter.JwtFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	CustomUserDetailsService userDetailsService;
	@Value("${roleAdmin}")
	String adminuser;
	@Value("${roleUser}")
	String userlevel;
	@Value("${Adminpwd}")
	String adminPwd;
	@Value("${userpwd}")
	String userPwd;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService);
		/*Authenticatioin with roles basic auth*/
		
		//auth.inMemoryAuthentication().withUser(adminuser).password(adminPwd).roles("ADMIN");
		//auth.inMemoryAuthentication().withUser(userlevel).password(userPwd).roles("USER");
	}
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	/*Authorization and permission*/
    	
    	//http.csrf().disable().
    	//http.csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
    	
    	
    	//AUTH CONFIG WITH JPA
    	http.csrf().disable().authorizeRequests().antMatchers("/user/authenticate/**").permitAll().antMatchers("/user/findAll/**").permitAll().
    	
    	antMatchers("/user/save/**").hasAnyRole("ADMIN").anyRequest().fullyAuthenticated().and().httpBasic();
    	
    	//AUTH CONFIG WITH JWT
    	
    	//http.csrf().disable().authorizeRequests().antMatchers("/user/authenticate/**").permitAll().antMatchers("/user/save/**").hasAnyRole("ADMIN").anyRequest().fullyAuthenticated().and().
    	//sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    	
    	
    	//http.csrf().disable().authorizeRequests().antMatchers("/").permitAll();
    	//http.csrf().disable().authorizeRequests().antMatchers("/login/**").not().hasAnyAuthority(authorities)
    	 http.csrf().disable();
    	    http.headers().frameOptions().disable();
    	    //http.csrf().disable().authorizeRequests().antMatchers("/user/authenticate/**").permitAll();
    	    
		/*
		 * http.csrf().disable().authorizeRequests().antMatchers("/authenticate")
		 * .permitAll().anyRequest().authenticated()
		 * .and().exceptionHandling().and().sessionManagement()
		 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 * http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 */
    }
   
}
