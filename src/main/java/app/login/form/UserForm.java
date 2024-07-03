package app.login.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
	
	// Validationとして桁数を設定
	@NotBlank
	@Size(min= 5, max = 255, message = "ユーザーネームは5～255桁で入力してください")
	private String username;
	@NotBlank
	@Size(min= 10, max = 255, message = "パスワードは10～255桁で入力してください")
	private String password;
}
