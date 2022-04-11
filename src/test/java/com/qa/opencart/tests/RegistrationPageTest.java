package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest{

	@BeforeClass
	public void regPageSetup() {
		registrationPage =loginPage.navigateToRegisterPage();
	}
	public String getRandomEmail() {
		Random random = new Random ();
		String email = "jeetautomation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	/*
	@DataProvider
	public Object [][] getRegistrationData() {
		return new Object [][] {
			{"Ram","Mohan", "457859622","testram", "yes"},
			{"Raj","Kumar", "458622522","testraj", "no"},
			{"jeetendra","Kumar","4789235521","testjeet","yes"},
		};
	}
	*/
	@DataProvider
	public Object [][] getRegistrationData() {
		Object regData [][] = ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	
	@Test(dataProvider = "getRegistrationData")
	public void userRegistrationTest(String firstName, String lastName,  String telephone,
			String password, String subscribe) {
		Assert.assertTrue(registrationPage.accountRegisteration(firstName,lastName,getRandomEmail(),telephone,
				password,subscribe));
	}
	
	
	
	
	
}
