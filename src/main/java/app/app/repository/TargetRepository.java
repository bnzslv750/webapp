package app.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.app.entity.TargetEntity;

@Repository
public interface TargetRepository extends JpaRepository<TargetEntity, String> {
	// insertかupdateかの判別用のcount
	@Query(value="select count(*) from target_t as t where t.username = :username",nativeQuery=true)
	int countTargetNum(@Param("username")String username);
	
	// 目標値を取得
	@Query(value="select t.target from target_t as t where t.username = :username",nativeQuery=true)
	int selectTargetNum(@Param("username")String username);
	
	// 目標値を更新
	@Query(value="update target_t as t set t.target = :target where t.username = :username",nativeQuery=true)
	@Modifying
	@Transactional
	int updateTargetNum(@Param("username")String username,@Param("target")int target);
	
}
