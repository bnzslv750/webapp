package app.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.app.entity.NumericEntity;
import app.app.form.NumericForm;
import app.app.repository.NumericRepository;

@Service
public class IndexLogicImpl implements IndexLogic {
	
	@Autowired
	NumericRepository numericRepository;
	
	@Autowired
	ModelMapper mapper;
	
	// ユーザーネームを引数としてもらい、フォームをリスト化し、返り値にするメソッドを作る
	public List<NumericForm> thisMonth(String username){
		
		// 今月のデータを取得するための引数として今年、今月、今月の日数を用意
		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonth().getValue();
		int numberOfDate = getThisMonthDate();
		
		// numeric_tから取得、登録するためのEntity
		List<NumericEntity> entityList = new ArrayList<>();
		
		// 今月のデータを取得し、entityに代入
		entityList = numericRepository.findNumericByUsername(username, year, month);
		
		// ビューに返却するためのFormを宣言
		List<NumericForm> formList = new ArrayList<>();
		
		// 今月のデータがまだ存在しない場合は、登録する
		if(entityList.size() < 28) {
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

		return formList;
	}

	// 今月の日数を取得するメソッド
	public int getThisMonthDate() {
		//現在を取得
		LocalDate localDate = LocalDate.now();
		//今年を取得
		int year = localDate.getYear();
		//今月を取得
		int month = localDate.getMonth().getValue();
		
        Calendar cal = Calendar.getInstance();
        cal.clear();
        
        //先程取得した今年、今月をカレンダーにセット
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        
        //月末の日数を取得
        int date = cal.getActualMaximum(Calendar.DATE) - 1;
        // 月末の日数を返却
        return date;
	}
}
