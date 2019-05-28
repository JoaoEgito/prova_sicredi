package com.sicredi;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sicredi.pages.GroceryCrudPage;
import com.sicredi.pages.Page;

public class GroceryAddPage extends Page {

 
	public GroceryAddPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		assertThat(driver.getCurrentUrl(), is("https://www.grocerycrud.com/demo/bootstrap_theme_v4/add"));
	}
	
	public void fillName(String name, String lastName) {
		driver.findElement(By.name("customerName")).sendKeys(name);
		driver.findElement(By.name("contactLastName")).sendKeys(lastName);
	}

	public void fillFirstContact(String firstName, String phone) {
		driver.findElement(By.name("contactFirstName")).sendKeys(firstName);
		driver.findElement(By.name("phone")).sendKeys(phone);

	}

	public void fillAddress(String line1, String line2, String city, String state, String postalCode, String country) {
		driver.findElement(By.name("addressLine1")).sendKeys(line1);
		driver.findElement(By.name("addressLine2")).sendKeys(line2);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("postalCode")).sendKeys(postalCode);
		driver.findElement(By.name("country")).sendKeys(country);

	}

	public void fillEmployeer(String employeer) {
		WebElement choosenEmployeer = driver.findElement(By.id("field_salesRepEmployeeNumber_chosen"));
		choosenEmployeer.click();
		choosenEmployeer.findElement(By.className("chosen-search"))//
				.findElement(By.tagName("input"))//
				.sendKeys(employeer + Keys.ENTER);

	}

	public void fillCreditLimit(int limit) {
		driver.findElement(By.name("creditLimit")).sendKeys(String.valueOf(limit));

	}

	public void pressSaveButton() {
		driver.findElement(By.id("form-button-save")).click();

	}


	public GroceryCrudPage clickAtGoBack() {
		driver.findElement(By.partialLinkText("Go back to list")).click();
		sleep(1000);
		
		return new GroceryCrudPage(driver);
	}

	public void verifySucessfullyMessageIsShowed() {
		
		assertThat(getMessage().startsWith("Your data has been successfully stored into the database."),
				is(true));
	}

	private String getMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement successDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("report-success")));
		return successDiv.findElements(By.xpath("./p[text()]")).get(0).getText();

	}

}
