package com.qa.opencart.tests;

import org.testng.Assert;
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

public class LoginPageTest extends BaseTest {

	
	@Test
	@Description("login page title test...")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println(actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE,Errors.LOGIN_PAGE_TITLE_MISMATCH);
	}
	
	@Test
	@Description("login page url test...")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		System.out.println("login page url :" + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	@Description("check forward pwd link Test...")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test
	@Description("login title testt with correct usernamd and pwd...")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
	   accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	   Assert.assertTrue(accPage.isAccountPageHeaderExist());
	}
	@Test
	@Description("register link test...")
	@Severity(SeverityLevel.CRITICAL)
	public void isRegisterLinkExist() {
	Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
}
