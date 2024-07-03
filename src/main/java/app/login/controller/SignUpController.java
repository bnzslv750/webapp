package app.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import app.login.entity.UserEntity;
import app.login.form.UserForm;
import app.login.repository.UserRepository;
import app.login.service.UserDetailsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SignUpController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	// 新規登録画面へ遷移
	@GetMapping("/signup")
	public ModelAndView initSignUp() {
		// ModelAndViewオブジェクトを生成
		ModelAndView mav = new ModelAndView();
		// データの受け渡しに使うためのフォームを宣言
		UserForm userForm = new UserForm();
		//  userFormという名前でUserFormを使う
		mav.addObject("userForm",userForm);
		// signup.htmlを表示する
		mav.setViewName("app/signup");
		// ModelAndViewを返却
		return mav;
	}
	
	// 新規登録ボタン押下後
	@PostMapping("/signup")
	public String signUp(@Validated @ModelAttribute UserForm userForm, BindingResult br,Model model,HttpServletRequest request) {
		// Validationチェック
		if(br.hasErrors()) {
			// Validationのエラーがあった場合、新規登録画面を再表示
			return "app/signup";
		}
		
		try {
			// 登録データが既に存在するか確認
			UserEntity user = userRepository.findByUsername(userForm.getUsername());
			// すでにユーザーネームが存在した場合、エラーメッセージを表示し、新規登録画面を再表示
			if(user != null) {
				// Thymeleafで表示するためのsignupErrorにエラーメッセージを追加
				model.addAttribute("signupError","ユーザーネームが既に存在します。");
				// 画面を再表示
				return "app/signup";
			}
			// ユーザーの登録メソッドを実行
			userDetailsServiceImpl.save(userForm);
		} catch (DataAccessException e) {
			// Thymeleafで表示するためのsignupErrorにエラーメッセージを追加
			model.addAttribute("signupError","ユーザーの登録に失敗しました。");
			// signup.htmlを再表示
			return "app/signup";
		}
		
		try {
			// HttpServletRequestのloginメソッドにユーザーネームとパスワードを渡すことでログインできる
			request.login(userForm.getUsername(),userForm.getPassword());
		}catch(ServletException e) {
			// 例外処理
			e.printStackTrace();
		}
		// /のGetMappingへ遷移
		return "fredirect:/";
	}
	
}
