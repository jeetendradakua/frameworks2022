package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;
//0. constructor of the class

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
		jsUtil  = new JavaScriptUtil(driver);
	}

//1.

	public By getBy(String locatorType, String locatorValue) {

		By locator = null;

		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "className":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "linkText":
			locator = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagName":
			locator = By.tagName(locatorValue);
			break;
		default:
			break;
		}
		return locator;
	}

	// 2.

	/*
	 public WebElement getElement(By locator) {
	 
		return driver.findElement(locator);

	}
*/
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
		jsUtil.flash(element);
	}
		return element;
		
	}	
		

		
		
	
	// 3.

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	// 4.

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	// .5

	public void doClick(By locator) {
		getElement(locator).click();
	}

	// 6.

	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	// 7.

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	// 8.

	public boolean isElementPresent(By locator) {
		if (getElements(locator).size() > 0) {
			return true;
		}
		return false;
	}

	// 9.

	public List<String> getLinksTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty())

			{
				eleTextList.add(text);
			}
		}
		return eleTextList;

	}
	
	public List<String> getElementAttributeList(By locator, String attrName) {

		List<WebElement> eleList = getElements(locator);
		List<String> eleAttrList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attrVal = e.getAttribute(attrName);
			System.out.println(attrVal);
			eleAttrList.add(attrVal);
		}
		return eleAttrList;
	}

	
	// ************Drop Down Utils*************************


	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);

	}

	// 13.

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	// 14.

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	// 15.

	public List<String> doGetDropDownOptions(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		List<String> optionsValList = new ArrayList<String>();

		System.out.println(optionsList.size());
		for (WebElement e : optionsList) {
			String text = e.getText();
			// System.out.println(text);
			optionsValList.add(text);
		}

		return optionsValList;

	}

	// 16.

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(driver.findElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	// ****************Actions utils*******************

	public void selectSubMenu(By parentMenu, By childMenu) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		getElement(childMenu).click();

	}

	public void selectSubMenu(By parentMenu, By childMenu, By subChildMenu) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(childMenu)).perform();
		Thread.sleep(2000);

		getElement(subChildMenu).click();

	}

	public void selectSubMenu(By parentMenu, By childMenu, By subChildMenu1, By subChildMenu2)
			throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(childMenu)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(subChildMenu1)).perform();
		Thread.sleep(2000);

		getElement(subChildMenu2).click();

	}

	public void doContextClick(By locator) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator)).perform();
	}

	public int getRightClickOptionsCount(By rightClick, By rightClickOptions) {
		return getRightClickOptionsList(rightClick, rightClickOptions).size();
	}

	public List<String> getRightClickOptionsList(By rightClick, By rightClickOptions) {
		List<String> rightClickItems = new ArrayList<String>();
		doContextClick(rightClick);

		List<WebElement> itemsList = getElements(rightClickOptions);

		System.out.println(itemsList.size());

		for (WebElement e : itemsList) {
			String text = e.getText();
			System.out.println(text);
			rightClickItems.add(text);
		}
		return rightClickItems;
	}

	public void selectRightClickMenu(By rightClick, By rightClickOptions, String value) {

		doContextClick(rightClick);
		List<WebElement> itemsList = getElements(rightClickOptions);

		System.out.println(itemsList.size());

		for (WebElement e : itemsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals("value")) {
				e.click();
				break;
			}
		}
	}
	/**
	 * Clicks in the middle of the given element. Equivalent to:
	 * Actions.moveToElement(onElement).click()
	 * 
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	/**
	 * Equivalent to calling: Actions.click(element).sendKeys(keysToSend).
	 * 
	 * @param locator
	 * @param value
	 */
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	// ********************Wait Utils *********************************

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout)); // sel 4.x

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementToBeVisible(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout)); // sel 4.x

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	// ******* with pollingTime*******

	public WebElement waitForElementPresent(By locator, int timeout, long pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(pollingTime)); // sel
																														// 4.x
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @param pollingTime
	 * @return
	 */
	public WebElement waitForElementToBeVisible(By locator, int timeout, long pollingTime) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(pollingTime)); // sel
																														// 4.x

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	// ************wait utils for non web elements (alert)**************

	public Alert waitForAlert(int timeOUt) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOUt));

		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public void acceptAlert(int timeOUt) {

		waitForAlert(timeOUt).accept();

	}

	public void dismissAlert(int timeOUt) {

		waitForAlert(timeOUt).dismiss();

	}

	public String getAlertText(int timeOUt) {

		return waitForAlert(timeOUt).getText();

	}
//*********************** WaitUtils For Url****************
	


	
	public  String waitForUrl (int timeOut, String urlFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut)); 

		if(wait.until(ExpectedConditions.urlContains(urlFraction))){
        return driver.getCurrentUrl();

	}
	return null;
	
	}
	
	
	public  String waitForUrlToBe (int timeOut, String urlVaL) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut)); 

		if(wait.until(ExpectedConditions.urlToBe(urlVaL))){
        return driver.getCurrentUrl();

	}
	return null;
	
	}
	public String waitForTitleContains(int timeOut, String titleFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		} else {
			System.out.println("title is not correct.....");
			return null;
		}
	}

	public String waitForTitleIs(int timeOut, String titleVal) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(titleVal))) {
			return driver.getTitle();
		} else {
			System.out.println("title is not correct.....");
			return null;
		}
	}
	
	public WebDriver waitForFrameByLocator(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public WebDriver waitForFrameByIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public WebDriver waitForFrameByString(int timeOut, String frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public WebDriver waitForFrameByElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
	}
	
	public void printAllElementsText(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		for(WebElement e : eleList) {
			System.out.println(e.getText());
		}
	}
	
	public List<String> getElementsTextListWithWait(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}
	
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	//**************************custom wait**************************
	

	public  WebElement retryingElement(By locator, int timeOut) {

		WebElement element = null;

		int attempts = 0;
		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt : " + attempts + ":" + locator);
				try {
					Thread.sleep(500); // default interval time
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
			}
			attempts++;

		}
		if (element == null) {
			try {

				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			} catch (Exception e) {
				System.out.println("element is not found exception... tried for :" + timeOut
						+ " with the interval of : " + 500 + "ms");
			}
		}

		return element;
	}

	public  WebElement retryingElement(By locator, int timeOut, int intervalTime) {

		WebElement element = null;

		int attempts = 0;
		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt : " + attempts + ":" + locator);
				try {
					Thread.sleep(intervalTime); // custom interval time
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
			}
			attempts++;

		}
		if (element == null) {
			try {

				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			} catch (Exception e) {
				System.out.println("element is not found exception... tried for :" + timeOut
						+ " with the interval of : " + intervalTime + "ms");
			}
		}

		return element;
	}

	public  void waitForPageLoad(int timeOut) {
		
		
		long endTime = System.currentTimeMillis() + timeOut ;
		while( System.currentTimeMillis()< endTime) {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
	    String state =  js.executeScript("return document.readyState").toString();
		System.out.println(state);	
	    if(state.equals("complete")) {
	    	break;
			}
		}
	}
	
	public  WebElement waitForElementPresentWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class,
						ElementNotInteractableException.class);
		
	return	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		
	}
	
	
	public  WebElement waitForElementVisiblityWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class);
		
	return	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	

}
	public  WebDriver  waitForFrameWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class,
						 NoSuchFrameException.class);
		
	return	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		
		
	}
	
	public  Alert waitForAlertFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoAlertPresentException.class);
		
	return	wait.until(ExpectedConditions.alertIsPresent());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	

