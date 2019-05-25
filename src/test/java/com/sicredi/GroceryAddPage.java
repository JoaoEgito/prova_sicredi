package com.sicredi;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sicredi.pages.GroceryCrudPage;
import com.sicredi.pages.Page;

public class GroceryAddPage extends Page {

	
	@FindBy(how = How.ID, using = "report-success")
	private WebElement successMessage;
 
	public GroceryAddPage(WebDriver driver) {
		super(driver);
	}
	
	public void fillName(String name, String lastName) {
		driver.findElement(By.name("customerName")).sendKeys(name);
		driver.findElement(By.name("contactLastName")).sendKeys(lastName);
	}

	public void fillFirstContact(String firstName, String phone) {

	}

	public void fillAddress(String line1, String line2, String city, String state, String postalCode) {

	}

	public void fillEmployeer(String employeer) {
		WebElement choosenEmployeer = driver.findElement(By.id("field_salesRepEmployeeNumber_chosen"));
		choosenEmployeer.click();
		choosenEmployeer.findElement(By.className("chosen-search"))//
				.findElement(By.tagName("input"))//
				.sendKeys(employeer + Keys.ENTER);

	}

	public void fillCreditLimit(int limit) {

	}

	public void pressSaveButton() {
		driver.findElement(By.id("form-button-save")).click();

	}

	public String getMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement successDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("report-success")));
		return successDiv.findElements(By.xpath("./p[text()]")).get(0).getText();

	}

	public void clickAtGoBack() {
		//click
		
	}


}
