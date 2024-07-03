package app.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.app.repository.NumericRepository;

@Controller
public class appController {
	
	@Autowired
	NumericRepository numericRepository;
	
	// 朝食分更新処理
	@PostMapping("/morning_set")
	public String setMorning(@RequestParam(value="year") String year,@RequestParam(value="month") String month,
							@RequestParam(value="date") String date,@RequestParam(value="morning_numeric") String morning_numeric,
							@RequestParam(value="breakfast") String breakfast,@RequestParam(value="morning_insulin") String morning_insulin) {
		// DBに登録するためのユーザーネームを取得
		String uname = SecurityContextHolder.getContext().getAuthentication().getName();
		
		// アップデートしたい日付を取得
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(date);
		
		// UPDATE文を実行
		numericRepository.updateMorning(uname, y, m, d, morning_numeric, breakfast, morning_insulin);
		
		// 初期表示処理を呼び出し、indexを再表示
		return "redirect:/";
	}
	
	// 昼食分更新処理
	@PostMapping("/noon_set")
	public String setNoon(@RequestParam(value="year") String year,@RequestParam(value="month") String month,
							@RequestParam(value="date") String date,@RequestParam(value="noon_numeric") String noon_numeric,
							@RequestParam(value="lunch") String lunch,@RequestParam(value="noon_insulin") String noon_insulin) {
		// DBに登録するためのユーザーネームを取得
		String uname = SecurityContextHolder.getContext().getAuthentication().getName();
		
		// アップデートしたい日付を取得
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(date);
		
		// UPDATE文を実行
		numericRepository.updateNoon(uname, y, m, d, noon_numeric, lunch, noon_insulin);
		
		// 初期表示処理を呼び出し、indexを再表示
		return "redirect:/";
	}
	
	@PostMapping("/evening_set")
	public String setEvening(@RequestParam(value="year") String year,@RequestParam(value="month") String month,
							@RequestParam(value="date") String date,@RequestParam(value="evening_numeric") String evening_numeric,
							@RequestParam(value="dinner") String dinner,@RequestParam(value="evening_insulin") String evening_insulin) {
		// DBに登録するためのユーザーネームを取得
		String uname = SecurityContextHolder.getContext().getAuthentication().getName();
		
		// アップデートしたい日付を取得
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(date);
		
		// UPDATE文を実行
		numericRepository.updateEvening(uname, y, m, d, evening_numeric, dinner, evening_insulin);
		
		// 初期表示処理を呼び出し、indexを再表示
		return "redirect:/";
	}
	
	// オプション画面へ
	@GetMapping("/option")
	public String goOption() {
		return "app/option";
	}
	
	// オプション画面からトップへ
	@GetMapping("/backIndex")
	public String backIndex() {
		return "redirect:/";
	}
}
