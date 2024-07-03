package app.login.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import app.app.form.NumericForm;
import app.app.service.IndexLogicImpl;


@Controller
public class LoginController {
	
	@Autowired
	IndexLogicImpl indexLogicImpl;
	
	// ログイン画面へ遷移
	@GetMapping("/login")
	public String Login() {
		return "app/login";
	}
	
	// ログイン後遷移処理
	@PostMapping("/login")
	public String postLogin(Model model) {
		// /のGetMappingへ遷移
		return "fredirect:/";
	}
	
	// ルートURLに対するGETリクエスト処理
	@GetMapping("/")
	public String index(Model model){
		// 画面表示用のフォームを取得
		List<NumericForm> numericForm = new ArrayList<>();
		// ログイン中のユーザー名を取得
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		// ユーザー名をキーに初期表示用のフォームを取得
		numericForm = indexLogicImpl.thisMonth(name);
		// Thymeleafで処理する用
		model.addAttribute("numericForm",numericForm);
		// ヘッダーに表示する用のユーザーネーム
		model.addAttribute("username",name);
		// 画面表示
		return "app/index";
	}
}
