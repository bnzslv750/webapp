package app.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.login.entity.UserEntity;
import app.login.form.UserForm;
import app.login.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// loadUserByUsernameメソッドをオーバーロードし、ユーザーを取得する
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		// user_tテーブルを扱うユーザーEntity
		UserEntity userEntity = new UserEntity(); 
		
		// ユーザーネームをもとにデータを取得
		userEntity = userRepository.findByUsername(userName);
		
		// データがなければ例外を投げる
		if(userEntity == null) {
			throw new UsernameNotFoundException("username :" + userName + "not found");
		}
		
		return new UserDetailsImpl(userEntity);
	}
	
	// 排他制御のためのアノテーション
	@Transactional
	public void save(UserForm userForm) {
		// DBとデータをやり取りするためのエンティティを定義
		UserEntity userEntity = new UserEntity();
		// 引数のフォームからユーザーネームをセット
		userEntity.setUsername(userForm.getUsername());
		// 引数のフォームからハッシュ化したパスワードをセット
		userEntity.setPassword(passwordEncoder.encode(userForm.getPassword()));
		// 保存
		userRepository.save(userEntity);
	}
}
