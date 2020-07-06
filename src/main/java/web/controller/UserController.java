package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/")
	public String getUsers(ModelMap model){
		model.addAttribute("allUsers", userService.getUsers());
		model.addAttribute("user", new User());
		return "users";
	}

	@PostMapping(value = "/adduser")
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelMap model){
		userService.addUser(user);
		return "redirect:/";
	}

	@GetMapping(value = "/update/{id}")
	public String updateUser(@PathVariable long id, ModelMap model){
		model.addAttribute("user", userService.getUserById(id));
		return "update";
	}

	@PostMapping(value = "/update/{id}")
	public String update(@PathVariable long id, @Valid @ModelAttribute User user){
		userService.updateUserById(id, user);
		return "redirect:/";
	}

	@GetMapping(value = "/deleteuser/{id}")
	public String deleteUser(@PathVariable long id, ModelMap model){
		userService.deleteUserById(id);
		return "redirect:/";
	}
}