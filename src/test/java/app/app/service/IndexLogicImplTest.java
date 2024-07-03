package app.app.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.app.form.NumericForm;


@SpringBootTest
public class IndexLogicImplTest {
	NumericForm numericForm;
	@Autowired
	IndexLogicImpl indexLogicImpl;
	
	@BeforeEach
	public void JU() {
		numericForm = new NumericForm();
		//indexLogicImpl = new IndexLogicImpl();
	}
	@Test
	public void test1() {
		List<NumericForm> numericFormList = new ArrayList<>();
		numericFormList = indexLogicImpl.thisMonth("testuser");
		int date1_1 = numericFormList.get(0).getDate();
		int date1_2 = numericFormList.get(29).getDate();
		assertThat(date1_1, is(1));
		assertThat(date1_2, is(30));
	}
}
