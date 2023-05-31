package blog.example.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.config.WebSecurityConfig;
import blog.example.modle.dao.UserDao;
import blog.example.modle.entity.UserEntity;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public boolean createAccount(String userName,String password) {
		UserEntity userEntity = userDao.findByUserName(userName);
		
		if(userEntity == null) {
			userDao.save(new UserEntity(userName, password));
			WebSecurityConfig.addUser(userName, password);
			return true;
		} else {
			return false;
		}
	}
		
	public List<UserEntity> getAllAccounts() {
		return userDao.findAll();
	}
	
	public UserEntity selectById(String userName) {
		return userDao.findByUserName(userName);
	}

	
}
