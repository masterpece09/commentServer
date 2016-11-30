package com.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dao.UserRepository;
import com.entities.User;


@RestController 
@SessionAttributes({"password","name"})
public class LoginController { 
	 
	@Autowired  
	UserRepository userRepository;
 
	//This method is call in case of login succeded
		@RequestMapping("/connect")
	private Map<String, Object> login(@RequestParam String status, HttpServletRequest httpServletRequest) { 
			Map<String, Object> responseMessage=new HashMap<>();
			//System.out.println("status--------777---"+status);
			if(status.equals("failed")){
				responseMessage.put("status", "failed");
			}else{
				User user = getLogedUser(httpServletRequest);
				responseMessage.put("status", "succeeded");
				responseMessage.put("user", user);
			} 
			
			return responseMessage; 
		}
		
	//
	public User getLogedUser(HttpServletRequest httpServletRequest){
				
				HttpSession httpSession = httpServletRequest.getSession();
				SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
				
				String userName = securityContext.getAuthentication().getName();
				List<String> roles = new ArrayList<>();
				
				for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
					roles.add(ga.getAuthority());
				}
				
				User user = userRepository.findByUsername(userName);
				
				//user.setUsername(userName);
				
				
				//user.setRole(roles.get(0));
				
				return user;
		}
	
		
	@RequestMapping("/loggedOut")  
	public Boolean logOut(){ 
		return true; 
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(ModelMap model) {
        model.put("name", "in28Minutes");
        return "welcome";
    }
}