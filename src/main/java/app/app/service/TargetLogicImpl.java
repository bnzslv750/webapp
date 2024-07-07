package app.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.app.entity.TargetEntity;
import app.app.repository.TargetRepository;
@Service
public class TargetLogicImpl implements TargetLogic{
	@Autowired
	TargetRepository targetRepository;
	
	@Override
	public void setTarget(int target) {
		// 目標値をセットするための引数としてユーザー名を取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		// DBに目標値が存在するか確認
		int count = targetRepository.countTargetNum(username);
		// DBとやり取りするためのEntityクラスを設定
		TargetEntity te = new TargetEntity();
		// DBに目標値があるか否かで分岐
		if(count < 1) {
			// DBに目標値がない場合、登録
			te.setTarget(target);
			te.setUsername(username);
			targetRepository.save(te);
		} else {
			// DBに目標値がある場合、更新
			targetRepository.updateTargetNum(username, target);
		}
	}
	
	@Override
	public int selectTarget() {
		// 目標値を取り出すための引数を取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		// DBに該当の値があるかをcountで取得
		int targetCount = targetRepository.countTargetNum(username);
		// 返り値を設定
		int target = 0;
		// DBに目標値があれば取得し、なければ0のまま
		if(targetCount == 1) {
			target = targetRepository.selectTargetNum(username);
		}
		// 目標値を返却
		return target;
	}
}
