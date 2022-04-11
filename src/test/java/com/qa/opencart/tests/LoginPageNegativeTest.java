package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100-Design Login Page for Open Cart application")
@Story("US 101 -Design Login page features")
public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] getLoginNegativeData() {
		return new Object[][] { { "ghdftygs@gmail.com", "teste1hj" }, { "jeetendra452@gmail.com", "tetstyahj" },
				{ " ", "teijdshh" }, { " ", " " } };

	}

	@Test(dataProvider = "getLoginNegativeData")
	@Description("logint title with invalid credentials....")
	@Severity(SeverityLevel.NORMAL)
	public void loginInvalidTest(String userName, String password) {
		Assert.assertTrue(loginPage.doInvalidLogin(userName, password),Errors.LOGIN_PAGE_ERROR_MSG_NOT_DISPLAYED);
	}

	
	/*
	@Test
	public void test1() {
		int a=10;
		int b=20;
		int sum= a+b;
		Assert.assertEquals(sum, 40, "..sum is not correct..");
	}
	*/
}
