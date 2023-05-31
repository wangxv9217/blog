package blog.example.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.sql.Date;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.example.modle.entity.BlogEntity;
import blog.example.modle.entity.UserEntity;
import blog.example.service.BlogService;
import blog.example.service.UserService;

@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	BlogService blogService;
	
	@GetMapping("/all")
	public String getLoginPage(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String userName = auth.getName();
		
		UserEntity user = userService.selectById(userName);
		
		Long userId = user.getUserId();
		
		List<BlogEntity>blogList = blogService.selectByUserId(userId);
		
		model.addAttribute("blogList",blogList);
		model.addAttribute("userName",userName);
		return "blog.html";
	}
	
    //跳转到添加博客页面
	@GetMapping("/new-blog")
	public String getBlogCreatePage(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String userName = auth.getName();

		UserEntity user = userService.selectById(userName);

		Long userId = user.getUserId();


		model.addAttribute("userId",userId);

		model.addAttribute("userName",userName);

		return "new-blog.html";
	}
	
	//添加博客
	@PostMapping("/new-blog")
	public String register(@RequestParam String blogTitle,@RequestParam("blogImage") MultipartFile blogImage,@RequestParam String blogDate,@RequestParam String message,@RequestParam Long userId) {

		String fileName = blogImage.getOriginalFilename();

		try {
			File blogFile = new File("./src/main/resources/static/blog-image/"+fileName);
			
			byte[] bytes = blogImage.getBytes();

			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(blogFile));

			out.write(bytes);

			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		blogService.insert(blogTitle, fileName, blogDate, message, userId);

		return "redirect:/admin/blog/all";
	}
	

	
	//详细页面
	@GetMapping("/blog-content/{blogId}")
	public String getBlogUserDetailPage(@PathVariable Long blogId, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		BlogEntity blogs = blogService.selectByBlogId(blogId);
		
		String userName = auth.getName();
		
		UserEntity user = userService.selectById(userName);

		Long userId = user.getUserId();

		model.addAttribute("userId",userId);
		model.addAttribute("blogs",blogs);	
		return "blog-content.html";
	}
	
	
	
	
	
	
	//删除博客
	@PostMapping("/delete/{blogId}")
	public String blogDelete(@PathVariable Long blogId) {
		
		blogService.deleteBlog(blogId);
		
		return "redirect:/admin/blog/all";
	}
	
}
