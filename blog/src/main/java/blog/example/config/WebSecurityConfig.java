package blog.example.config;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import blog.example.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginPage("/admin/login")
				.defaultSuccessUrl("/admin/blog/all", true)
				.usernameParameter("userName")
				.passwordParameter("password")
				//.loginProcessingUrl("/user-login")
				.failureUrl("/admin/login?error")
				.permitAll()
		).logout(logout -> logout
		        .logoutSuccessUrl("/admin/login")
		).authorizeHttpRequests(authz -> authz
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
				.permitAll()
				.requestMatchers("/blog/**", "/admin/register", "/CSS/**","/js/**", "/login", "*.css", "*.js")
				.permitAll()
				.anyRequest()
				.authenticated()
		);
		
		return http.build();
	}
	
	public static InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	
	@Autowired
	private UserService userService;
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		List<UserDetails> users = userService.getAllAccounts().stream().map(
				
				account -> User
				.withDefaultPasswordEncoder()
				.username(account.getUserName())
				.password(account.getPassword())
				.roles("USER")
				.build()  
				).toList();
		
		manager = new InMemoryUserDetailsManager(users);
		
		
		return manager;
	}
	
	public static void addUser(String userName, String password) {
		manager.createUser(User
				.withDefaultPasswordEncoder()
				.username(userName)
				.password(password)
				.roles("USER")
				.build());
	}

}


