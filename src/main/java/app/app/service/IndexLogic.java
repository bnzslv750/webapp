package app.app.service;

import java.util.List;

import app.app.form.NumericForm;

public interface IndexLogic {
	public List<NumericForm> thisMonth(String username);
	
	public int getThisMonthDate();
}
