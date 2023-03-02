package com.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import Entities.User;

@ControllerAdvice
public class AdvisorController {

	 @ModelAttribute("registerUser")
	    public User getUserDefaults(){
	        return new User();
	    }
	
}
