package com.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Dao.userDao;
import com.helpers.Token;

import Entities.User;

@Controller
public class AuthController {
	
	@Autowired
	private userDao userDao;

	 @GetMapping("/login")
	    public ModelAndView getLogin(){
	        System.out.println("In Login Page Controller");
	        ModelAndView getLoginPage = new ModelAndView("login");
	        // Set Token String:
	        String token = Token.generateToken();
	        // Send Token to View:
	        getLoginPage.addObject("token", token);
	        getLoginPage.addObject("PageTitle", "Login");
	        return getLoginPage;
	    }

	    @PostMapping("/login")
	    public String login(@RequestParam("email")String email,
	                        @RequestParam("password") String password,
	                        @RequestParam("_token")String token,
	                        Model model,
	                        HttpSession session){

	        // TODO: VALIDATE INPUT FIELDS / FORM DATA:
	        if(email.isEmpty() || email == null || password.isEmpty() || password == null){
	            model.addAttribute("error", "Username or Password Cannot be Empty");
	            return "login";
	        }

	        // TODO: CHECK IF EMAIL EXISTS:
	        
	        User user = userDao.getUserByEmail(email);
	        
	        String getEmailInDatabase = user.getEmail();
	     

	        // Check If Email Exists:
	        if(getEmailInDatabase != null ){
	            // Get Password In Database:
	            String getPasswordInDatabase = user.getPassword();
	            
	            // Validate Password:
	            if(!BCrypt.checkpw(password, getPasswordInDatabase)){
	                model.addAttribute("error", "Incorrect Username or Password");
	                return "login";
	            }
	            // End Of Validate Password.
	        }else{
	            model.addAttribute("error", "Something went wrong please contact support");
	            return "error";
	        }
	        // Check If Email Exists.

	        // TODO : CHECK IF USER ACCOUNT IS VERIFIED:
	        int verified = user.getVerified();

	        // Check If Account is verified:
	        if (verified != 1){
	            String msg = "This Account is not yet Verified, please check email and verify account";
	            model.addAttribute("error", msg);
	            return "login";
	        }
	        // End Of Check If Account is verified.
	        
	        session.setAttribute("user", user);
	        session.setAttribute("token", token);
	        session.setAttribute("authenticated", true);

	        return "redirect:/app/dashboard";
	        
         }
	    
	    @GetMapping("/logout")
	    public String logout(HttpSession session, RedirectAttributes redirectAttributes){
	        session.invalidate();
	        redirectAttributes.addFlashAttribute("logged_out", "Logged out successfully");
	        return "redirect:/login";
	    }

}
