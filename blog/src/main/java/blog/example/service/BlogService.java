package blog.example.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.modle.dao.BlogDao;
import blog.example.modle.entity.BlogEntity;



@Service
public class BlogService {
	@Autowired
	BlogDao blogDao;
	
	//保存
	public void insert(String blogTitle, String fileName, String blogDate, String message, Long userId) {
		blogDao.save(new BlogEntity(blogTitle, fileName, blogDate, message, userId));
	}

	//一览
	public List<BlogEntity> selectByUserId(Long userId){
		return blogDao.findByUserId(userId);
	}	
	//详细
	public BlogEntity selectByBlogId(long blogId) {
		return blogDao.findByBlogId(blogId);
	}
	//更新
	public void update(Long blogId, String fileName, String blogTitle, String blogDate, String message, Long userId) {
		blogDao.save(new BlogEntity(blogTitle, fileName, blogDate, message, userId));
	}
	//用户博客一览
	public List<BlogEntity> selerByAll(){
		return blogDao.findAll();
	}
	//删除
	public List<BlogEntity>deleteBlog(Long blogId){
		return blogDao.deleteByBlogId(blogId);
	}
}
