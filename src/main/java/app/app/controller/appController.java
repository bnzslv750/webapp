package app.app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.app.form.NumericForm;
import app.app.repository.NumericRepository;
import app.app.service.IndexLogicImpl;
import app.app.service.OptionLogicImpl;
import app.app.service.TargetLogicImpl;

@Controller
public class appController {
	
	@Autowired
	NumericRepository numericRepository;
	
	@Autowired
	IndexLogicImpl indexLogicImpl;
	
	@Autowired
	OptionLogicImpl optionLogicImpl;
	
	@Autowired
	TargetLogicImpl targetLogicImpl;
	
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
	public String goOption(Model model) {
		// プルダウン用の年リストを作成
		List<Integer> year = new ArrayList<Integer>();
		// 2024年から今年までのリストを作る
		year = optionLogicImpl.yearPulldown();
		// ビューに渡す
		model.addAttribute("year",year);
		// プルダウン用の月リストを作成
		List<Integer> month = new ArrayList<Integer>();
		// 1月から12月まで作成
		for(int i = 1;i <= 12;i++) {
			month.add(i);
		}
		// ビューに渡す
		model.addAttribute("month",month);
		// ビューのテーブル用リストを作成
		List<NumericForm> numericForm = new ArrayList<NumericForm>();
		// リストの取得引数としてログインユーザーを取得
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		// 今月のデータを取得する処理
		numericForm = indexLogicImpl.thisMonth(name);
		// 今月のデータをビューに渡す
		model.addAttribute("NumericForm",numericForm);
		// 目標値を取得
		int target = targetLogicImpl.selectTarget();
		// 目標値をビューに渡す
		model.addAttribute("target",target);
		// option.htmlへ遷移
		return "app/option";
	}
	
	@PostMapping("/option")
	public String showNum(@RequestParam(value="year") String year,@RequestParam(value="month") String month,Model model) {
		// プルダウン用の年リストを作成
		List<Integer> pullYear = new ArrayList<Integer>();
		// 2024年から今年までのリストを作る
		pullYear = optionLogicImpl.yearPulldown();
		// ビューに渡す
		model.addAttribute("year",pullYear);
		// プルダウン用の月リストを作成
		List<Integer> pullMonth = new ArrayList<Integer>();
		// 1月から12月まで作成
		for(int i = 1;i <= 12;i++) {
			pullMonth.add(i);
		}
		model.addAttribute("month",pullMonth);		
		// 選択した年月を数値に変換
		int selectYear = Integer.parseInt(year);
		int selectMonth = Integer.parseInt(month);
		
		// 今年、今月を取得 
		int thisYear = LocalDate.now().getYear();
		int thisMonth = LocalDate.now().getMonth().getValue();
		
		// ビューに渡す用のリスト
		List<NumericForm> list = new ArrayList<NumericForm>();
		
		// 未来を見ようとしていたら、エラーメッセージを出力し今年のデータを表示
		if(selectYear == thisYear && selectMonth > thisMonth) {
			model.addAttribute("optionError","1");
			list = optionLogicImpl.getMonth(thisYear, thisMonth);
		} else {
			list = optionLogicImpl.getMonth(selectYear, selectMonth);
		}
		// ビューに渡す用のモデルに追加
		model.addAttribute("NumericForm",list);
		// 目標値を取得するためのユーザー名を取得
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		// 目標値を取得
		int target = targetLogicImpl.selectTarget();
		// 目標値をビューに渡す
		model.addAttribute("target",target);
		// option画面を表示
		return "app/option";
	}
	
	@PostMapping("/target")
	public String target(@RequestParam(value="target") String target) {
		int targetNum = Integer.parseInt(target);
		targetLogicImpl.setTarget(targetNum);
		return "redirect:/option";
	}
	
	// オプション画面からトップへ
	@GetMapping("/backIndex")
	public String backIndex() {
		return "redirect:/";
	}
}
