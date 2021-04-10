package com.pilotair.clogbook.webapp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pilotair.clogbook.core.entity.User;
import com.pilotair.clogbook.core.service.UserService;
import com.pilotair.clogbook.webapp.form.SignupForm;
import com.pilotair.clogbook.webapp.security.service.SecurityServiceInterface;

@Controller
public class HomeController {

	private Logger						logger	= LoggerFactory.getLogger( HomeController.class );

	@Autowired
	private UserService					UserService;

	@Autowired
	private SecurityServiceInterface	securityService;

	@ModelAttribute
	public SignupForm newSignupForm() {
		return new SignupForm();
	}

	@GetMapping( "/" )
	public String displayHomePage() {
		return "index";
	}

	@GetMapping( "/signup" )
	public String redirectSignUpGet() {
		return "redirect:/?signup";
	}

	@PostMapping( "/signup" )
	public String signup( @ModelAttribute @Valid final SignupForm signupForm,
	        final BindingResult results,
	        final RedirectAttributes redirectAttributes ) {
		System.err.println( "SIGN UP" );
		logger.info( "Signup request" );
		if ( results.hasErrors() ) {
			redirectAttributes
			        .addFlashAttribute( "org.springframework.validation.BindingResult.signupForm", results );
			redirectAttributes.addFlashAttribute( signupForm );
			return "redirect:/";
		}
		User user = UserService.registerUser( signupForm.buildUser() );
		logger.info( "User registered -> " + user.getUsername() );

		try {
			securityService.autoLogin( user.getUsername(), signupForm.getPassword() );
		} catch ( Exception e ) {
			logger.error( "Auto loging in user " + user.getUsername() + " failed" );
			e.printStackTrace();
		}

		return "redirect:/app/dashboard";
	}

}
