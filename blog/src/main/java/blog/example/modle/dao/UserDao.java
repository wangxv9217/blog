package blog.example.modle.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import blog.example.modle.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity,Long>{
	UserEntity findByUserName(String userName);
	
	UserEntity save(UserEntity userEntity);
	
	List<UserEntity> findAll();

	
}


	
