package com.Controller;

import java.util.List;
import java.util.Random;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Dao.userDao;
import javax.mail.MessagingException;
import com.MailMesseger.MailMessenger;
import com.helpers.HTML;
import com.helpers.Token;

import Entities.User;

@Controller
public class RegisterController {

	@Autowired
	private userDao dao;
	
	@GetMapping("/register")
    public ModelAndView getRegister(){
        ModelAndView getRegisterPage = new ModelAndView("register");
        getRegisterPage.addObject("PageTitle", "Register");
        System.out.println("In Register Page Controller");
        return getRegisterPage;
    }
	
	    @PostMapping("/register")
	    public ModelAndView register(@Valid @ModelAttribute("registerUser")User user,
	                                 BindingResult result,
	                                 @RequestParam("first_name") String first_name,
	                                 @RequestParam("last_name") String last_name,
	                                 @RequestParam("email") String email,
	                                 @RequestParam("password") String password,
	                                 @RequestParam("confirm_password") String confirm_password) throws MessagingException {

	        ModelAndView registrationPage = new ModelAndView("registrationError");

	        
	        // Check For Errors:
	        if(result.hasErrors() && confirm_password.isEmpty()){
	            registrationPage.addObject("confirm_pass", "The confirm Field is required");
	            return registrationPage;
	        }

	        // TODO: CHECK FOR PASSWORD MATCH:
	        if(!password.equals(confirm_password)){
	            registrationPage.addObject("passwordMisMatch", "passwords do not match");
	            return registrationPage;
	        }
	        
	        String token = Token.generateToken();

	        // TODO: GENERATE RANDOM CODE:
	        Random rand = new Random();
	        int bound = 123;
	        int code = bound * rand.nextInt(bound);

	        // TODO: GET EMAIL HTML BODY:
	        String emailBody = HTML.htmlEmailTemplate(token, code);
	        // TODO: HASH PASSWORD:
	        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
	        

               List<User> users = this.dao.getUser();
	        
	        for(User user1 : users) {
	        	if(user1.getEmail().equals(email)) {
	        		
	        		ModelAndView registrationErrorPage2 = new ModelAndView("registrationError2");
	        		
	        		return registrationErrorPage2;
	        	}
	        }
	     
	        // TODO: REGISTER USER:
	        
	        this.dao.createUser(user , token , code , hashed_password);
	        
	        // TODO: SEND EMAIL NOTIFICATION:
	        MailMessenger.htmlEmailMessenger("sagarkhandagre897@gmail.com", email, "Verify Account", emailBody);

	        ModelAndView registrationSuccessPage = new ModelAndView("registrationSuccess");
	        
	        return registrationSuccessPage ;
	 }
	
}
