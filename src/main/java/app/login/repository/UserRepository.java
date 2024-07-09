package app.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;

import app.login.entity.UserEntity;

//@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	UserEntity findByUsername(String username);
}