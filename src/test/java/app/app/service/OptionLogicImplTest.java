package app.app.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OptionLogicImplTest {
	
	@Autowired
	OptionLogicImpl optionLogicImpl;
	
	@Test
	public void test1() {
		List<Integer> list = new ArrayList<Integer>();
		list = optionLogicImpl.yearPulldown();
		int year = list.get(0);
		assertThat(year, is(2024));
	}
	
}
