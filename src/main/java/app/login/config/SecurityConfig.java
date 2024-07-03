package app.login.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	// パスワードをハッシュ化するpasswordEncoderをAutowiredで使えるようにする
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	// セキュリティフィルタチェーンの設定
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> auth
				// cssファイル、ログイン画面は認証不要
				.requestMatchers("/login","/css/***","/signup").permitAll()
				// その他のリクエストはすべて認証が必要
	        	.anyRequest().authenticated()
				)
			.formLogin(form -> form
				.loginProcessingUrl("/login") // ログイン認証を行うURL
				.loginPage("/login") // ログインページのURL
				.defaultSuccessUrl("/") //認証成功後のリダイレクト先(Mapping)
				.failureUrl("/login?error") // Thymeleafにより、ログイン失敗時にメッセージを表示可能
				)
			.logout(logout -> logout
					.logoutSuccessUrl("/login") // ログアウト成功時のURL
					.permitAll()); // ログアウトは認証必要なし
		// 設定を反映しHttpSecurityオブジェクトをビルド
		return http.build();
		}
	
	// マッピング用の設定
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
}
