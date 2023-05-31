package blog.example.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {
	@GetMapping("admin/blog")
	public ModelAndView getBlog(ModelAndView mav) {		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("name", user.getUsername());
		mav.setViewName("blog.html");
		return mav;
	}
}


