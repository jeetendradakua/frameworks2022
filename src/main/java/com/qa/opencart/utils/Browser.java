package com.qa.opencart.utils;

public interface Browser {

	public String CHROME_BROWSER_VALUE = "chrome";
	public String FIREFOX_BROWSER_VALUE = "firefox";
	public String SAFARI_BROWSER_VALUE = "safari";

	public String CHROME_DRIVER_BINARY_KEY = "webdriver.chrome.driver";
	public String FIREFOX_DRIVER_BINARY_KEY = "webdriver.gecko.driver";

	public String CHROME_DRIVER_PATH=".src/test/resources/drivers/chromedriver.exe";
	public String FIREFOX_DRIVER_PATH=".src/test/resources/drivers/geckodriver.exe";
	
	
}
