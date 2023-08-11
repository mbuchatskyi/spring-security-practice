package mbuchatskyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mbuchatskyi.exception.EntityNotFoundException;
import mbuchatskyi.model.User;
import mbuchatskyi.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String create(Model model) {
		model.addAttribute("user", new User());

		return "register";
	}

	@PostMapping("/register")
	public String create(@Validated @ModelAttribute("user") User user, BindingResult result) throws EntityNotFoundException {
		if (result.hasErrors()) {
			return "register";
		}
		
		if (userService.readByEmail(user.getEmail()) != null) {
			throw new EntityNotFoundException("The user with email " + user.getEmail() + " already exists.");
		}

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.create(user);
		return "redirect:/profile";
	}
}
