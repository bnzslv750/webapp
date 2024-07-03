package app.app.entity;

import app.app.form.NumericPKForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="numeric_t")
@IdClass(NumericPKForm.class)
public class NumericEntity {
	@Id
	@Column(name="username")
	private String username;
	@Id
	@Column(name="year")
	private int year;
	@Id
	@Column(name="month")
	private int month;
	@Id
	@Column(name="date")
	private int date;
	@Column(name="morning_numeric")
	private String morning_numeric;
	@Column(name="morning_insulin")
	private String morning_insulin;
	@Column(name="morning_flg")
	private String morning_flg;
	@Column(name="breakfast")
	private String breakfast;
	@Column(name="noon_numeric")
	private String noon_numeric;
	@Column(name="noon_insulin")
	private String noon_insulin;
	@Column(name="noon_flg")
	private String noon_flg;
	@Column(name="lunch")
	private String lunch;
	@Column(name="evening_numeric")
	private String evening_numeric;
	@Column(name="evening_insulin")
	private String evening_insulin;
	@Column(name="evening_flg")
	private String evening_flg;
	@Column(name="dinner")
	private String dinner;
	@Column(name="memo")
	private String memo;
}
