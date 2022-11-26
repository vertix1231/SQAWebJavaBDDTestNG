package com.test.kerja.sqa.sqawebjavabddtestng.driver;

import com.test.kerja.sqa.sqawebjavabddtestng.utils.ConstantsParam;

public class DriverStrategyImplementor {
	public static DriverStrategy chooseStrategy(String strategy) {
		switch (strategy) {
		case ConstantsParam.CHROME:
			return new Chrome();

		case ConstantsParam.FIREFOX:
			return new FireFox();

		default:
			return null;
		}
	}
}
