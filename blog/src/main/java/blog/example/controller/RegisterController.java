package blog.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.example.service.UserService;

@Controller
@RequestMapping("/admin/register")
public class RegisterController {
	
	@Autowired
	private UserService accountService;
	
	@GetMapping
	public String getRegister() {
		return "register.html";
	}
	
	@PostMapping
	public String register(@RequestParam String userName, @RequestParam String password) {
		if (accountService.createAccount(userName, password)) {
			return "redirect:/admin/login";
		} else {
			return "register.html";
		}
	}
}