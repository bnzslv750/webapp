package app.app.service;

import java.util.List;

import app.app.form.NumericForm;

public interface OptionLogic {
	public List<Integer> yearPulldown();
	
	public List<NumericForm> getMonth(int year, int month);
	
	public int getMonthDate(int year, int month);
}
