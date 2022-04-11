package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountsPageTest extends BaseTest{
  @Description("pre login for accounts page tests")
	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	@Description("accounts title page tests")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageTitleTest() {
		String actAccountPageTitle = accPage.getAccountsPageTitle();
	 System.out.println("Acc page title :" + actAccountPageTitle);
	Assert.assertEquals(actAccountPageTitle, Constants.ACCOUNTS_PAGE_TITLE);
	
	}
	@Test
	@Description("accounts page header tests")
	@Severity(SeverityLevel.NORMAL)
	public void accPageHeaderTest() {
		Assert.assertTrue(accPage.isAccountPageHeaderExist());
	}
	
	@Test
	@Description("Search exist tests")
	@Severity(SeverityLevel.CRITICAL)
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	
	@Test
	@Description("accounts Sections tests")
	@Severity(SeverityLevel.NORMAL)
	public void accSectionTest() {
		List<String>actSecList = accPage.getAccountsPageSectionsList();
		System.out.println("Account section list : "+ actSecList);
		Assert.assertEquals(actSecList, Constants.ACCOUNTS_SECTION_LIST);
	}
	
	@Test
	@Description("seach header tests")
	@Severity(SeverityLevel.NORMAL)
	public void searchHeaderTest() {
		searchResultsPage = accPage.doSearch("Macbook");
		String actSearchHeader = searchResultsPage.getResultsPageHeaderValue();
		Assert.assertTrue(actSearchHeader.contains("Macbook"));
	}
	
	@Test
	@Description("check product count  tests")
	@Severity(SeverityLevel.NORMAL)
	public void searchProductCountTest() {
		searchResultsPage = accPage.doSearch("Macbook");
		int actProductCount = searchResultsPage.getProductSearchCount();
		Assert.assertEquals(actProductCount, Constants.MACBOOK_PRODUCT_COUNT);
	}
	
	@Test
	@Description("Search list tests")
	@Severity(SeverityLevel.NORMAL)
	public void getSearchListTest() {
		searchResultsPage = accPage.doSearch("Macbook");
		List<String>actProductList = searchResultsPage.getProductResultsList();
		System.out.println(actProductList);
	}
	
}
