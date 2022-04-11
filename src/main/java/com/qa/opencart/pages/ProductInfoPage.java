package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
 
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("div#content img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By successMessg = By.cssSelector("div.alert.alert-success.alert-dismissible");
	private Map<String, String>productInfoMap;
	
	

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	 public String getProductHeaderText() {
		 return eleutil.doElementGetText(productHeader);
	 }
	 
	 public int getProductImagesCount() {
		 return eleutil.waitForElementsToBeVisible(productImages, Constants.DEFAULT_TIME_OUT).size();
	 }
	 
	 public Map<String, String> getProductInfo() {
		 
	productInfoMap = new LinkedHashMap<String,String>();
	productInfoMap.put("productname", getProductHeaderText());
	productInfoMap.put("productImagesCount",String.valueOf(getProductImagesCount()));
	getProductMetaData(); 
	getProductPriceData(); 		
		return productInfoMap;	
	}
		
		
		
		

	 
	 
	 private void getProductMetaData() {
	 
		 List<WebElement>metaDataList =  eleutil.getElements(productMetaData);
			for(WebElement e:metaDataList ) {
				String text = e.getText().trim();
				String meta[] = text.split(":");
				String metaKey = meta [0].trim();
				String metaValue = meta[1].trim();
				productInfoMap.put(metaKey, metaValue);
				
			
			}
	 
			}	 
	 
	 private void getProductPriceData() {
		 
		 List<WebElement>metaPriceList =  eleutil.getElements(productPriceData);
		 String price =  metaPriceList.get(0).getText().trim();
	  String exPrice = metaPriceList.get(1).getText().trim();
	  productInfoMap.put("price",price);
	  productInfoMap.put("exTaxPrice",exPrice);
			}
	 
}


