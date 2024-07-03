package app.app.form;

import lombok.Data;

@Data
public class NumericForm {
	private String username;
	private int year;
	private int month;
	private int date;
	private String morning_numeric;
	private String morning_insulin;
	private String morning_flg;
	private String breakfast;
	private String noon_numeric;
	private String noon_insulin;
	private String noon_flg;
	private String lunch;
	private String evening_numeric;
	private String evening_insulin;
	private String evening_flg;
	private String dinner;
	private String memo;
}
