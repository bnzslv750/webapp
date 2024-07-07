package app.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.app.entity.NumericEntity;
import app.app.form.NumericForm;
import app.app.repository.NumericRepository;

@Service
public class OptionLogicImpl implements OptionLogic {
	
	@Autowired
	NumericRepository numericRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<Integer> yearPulldown() {
		// プルダウン用のリストを作成
		List<Integer> list = new ArrayList<Integer>();
		// 今年を取得
		int year = LocalDate.now().getYear();
		for(int i = 2024;i <= year;i++) {
			// 2024年から今年までのリストを作成
			list.add(i);
		}
		// 年のリストを返却
		return list;
	}
	
	@Override
	// ユーザーネームを引数としてもらい、フォームをリスト化し、返り値にするメソッドを作る
	public List<NumericForm> getMonth(int year, int month){
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		// 対象月のデータを取得するための引数として対象月の日数を用意
		int numberOfDate = getMonthDate(year, month);
		
		// numeric_tから取得、登録するためのEntity
		List<NumericEntity> entityList = new ArrayList<>();
		
		// 対象月のデータを取得し、entityに代入
		entityList = numericRepository.findNumericByUsername(username, year, month);
		
		// ビューに返却するためのFormを宣言
		List<NumericForm> formList = new ArrayList<>();
		
		// 対象月のデータがまだ存在しない場合は、登録する
		if(entityList.size() < 26) {
			NumericEntity ne = new NumericEntity();
			ne.setUsername(username);
			ne.setYear(year);
			ne.setMonth(month);
			// 日付データをインサート
			for(int i = 1;i <= numberOfDate;i++) {
				ne.setDate(i);
				numericRepository.save(ne);
				entityList.add(ne);
				formList.add(mapper.map(ne, NumericForm.class));
			}
		} else {
				
			for(int i = 0; i < entityList.size();i++) {
				formList.add(mapper.map(entityList.get(i), NumericForm.class));
			}
		}
		
		// formを返却
		return formList;
	}
	
	@Override
	// 対象月の日数を取得するメソッド
	public int getMonthDate(int year, int month) {
		
        Calendar cal = Calendar.getInstance();
        cal.clear();
        
        //対象の年月をカレンダーにセット
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month - 1);
        
        //月末の日数を取得
        int date = cal.getActualMaximum(Calendar.DATE);
        // 月末の日数を返却
        return date;
	}

}
