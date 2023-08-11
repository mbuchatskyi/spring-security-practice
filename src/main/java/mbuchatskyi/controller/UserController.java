package mbuchatskyi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mbuchatskyi.exception.EntityNotFoundException;
import mbuchatskyi.model.User;
import mbuchatskyi.service.UserService;

@Controller
@RequestMapping("/profile")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public String read(Principal principal, Model model) {
		User authorizedUser = userService.readByEmail(principal.getName());

		model.addAttribute("user", authorizedUser);
	
		return "user-profile-read";
	}
	
	@GetMapping("/edit")
	public String editGet(Principal principal, Model model) {
		User authorizedUser = userService.readByEmail(principal.getName());

		model.addAttribute("user", authorizedUser);
	
		return "user-profile-edit";
	}
	
	@PostMapping("/edit")
	public String editPost(Principal principal, @Validated @ModelAttribute("user") User user, BindingResult result, 
			Model model) throws EntityNotFoundException {
		
		if (result.hasErrors()) {
			return "user-profile-edit";
		}
		
		if (userService.readByEmail(user.getEmail()) != null) {
			throw new EntityNotFoundException("The user with email " + user.getEmail() + " already exists.");
		}
		
		User authorizedUser = userService.readByEmail(principal.getName());
		
		authorizedUser.setName(user.getName());
		authorizedUser.setAddress(user.getAddress());
		authorizedUser.setEmail(user.getUsername());
	
		userService.update(authorizedUser); 

		return "user-profile-read";
	}
}
