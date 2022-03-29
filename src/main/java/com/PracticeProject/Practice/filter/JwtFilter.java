package com.PracticeProject.Practice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.PracticeProject.Practice.Service.CustomUserDetailsService;
import com.PracticeProject.Practice.util.JwtUtil;
@Component
public class JwtFilter extends OncePerRequestFilter{
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationCode=request.getHeader("Authorization");
		
		String token=null;
		String userName=null;
		if(authorizationCode!=null && authorizationCode.startsWith("Bearer "))
		{
			token=authorizationCode.substring(7);
			userName=jwtUtil.extractUsername(token);
		}
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()!=null)
		{
			UserDetails userDetails=customUserDetailsService.loadUserByUsername(userName);
			if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
		}
		filterChain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}

}
