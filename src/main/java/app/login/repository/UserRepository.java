package app.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;

import app.login.entity.UserEntity;

//@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	// usernameでDBからデータを検索
	/*@Query(value="select * from user_t as u where u.username = :username",nativeQuery = true)
	UserEntity findByUsername(@Param("username") String username);*/
	
	UserEntity findByUsername(String username);
}