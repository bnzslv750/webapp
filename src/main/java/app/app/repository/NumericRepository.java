package app.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.app.entity.NumericEntity;
import app.app.form.NumericPKForm;

@Repository
public interface NumericRepository extends JpaRepository<NumericEntity, NumericPKForm> {
	
	// 初期表示処理で呼び出す
	@Query(value="select * from numeric_t as n where n.username = :username and n.year = :year and n.month = :month",nativeQuery = true)
	List<NumericEntity> findNumericByUsername(@Param("username") String username, @Param("year") int year, @Param("month") int month);
	
	// 朝分更新処理
	@Query(value="update numeric_t as n set n.morning_numeric = :morning_numeric, n.breakfast = :breakfast,n.morning_insulin = :morning_insulin, morning_flg = '1' where n.username = :username and n.year = :year and n.month = :month and n.date = :date", nativeQuery=true)
	@Modifying
	@Transactional
	int updateMorning(@Param("username") String username,@Param("year") int year,@Param("month") int month,@Param("date") int date,@Param("morning_numeric")String morning_numeric,@Param("breakfast") String breakfast,@Param("morning_insulin") String morning_insulin);
	
	//昼分更新処理
	@Query(value="update numeric_t as n set n.noon_numeric = :noon_numeric, n.lunch = :lunch,n.noon_insulin = :noon_insulin, noon_flg = '1' where n.username = :username and n.year = :year and n.month = :month and n.date = :date", nativeQuery=true)
	@Modifying
	@Transactional
	int updateNoon(@Param("username") String username,@Param("year") int year,@Param("month") int month,@Param("date") int date,@Param("noon_numeric")String noon_numeric,@Param("lunch") String lunch,@Param("noon_insulin") String noon_insulin);
	
	//夕方分更新処理
	@Query(value="update numeric_t as n set n.evening_numeric = :evening_numeric, n.dinner = :dinner,n.evening_insulin = :evening_insulin, evening_flg = '1' where n.username = :username and n.year = :year and n.month = :month and n.date = :date", nativeQuery=true)
	@Modifying
	@Transactional
	int updateEvening(@Param("username") String username,@Param("year") int year,@Param("month") int month,@Param("date") int date,@Param("evening_numeric")String evening_numeric,@Param("dinner") String dinner,@Param("evening_insulin") String evening_insulin);

}
