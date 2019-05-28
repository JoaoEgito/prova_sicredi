package com.sicredi.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sicredi.GroceryAddPage;


public class GroceryCrudPage extends Page {

	@FindBy(how = How.ID, using = "switch-version-select")
	private WebElement switchVersionSelect;

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Add Customer")
	private WebElement addCustumerBtn;
	
	@FindBy(how = How.CLASS_NAME, using = "search-button")
	private WebElement searchBtn;
	
	@FindBy(how = How.CLASS_NAME, using = "select-all-none")
	private WebElement selectAllCheck;

	public GroceryCrudPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(driver, this);
	}

	public void changeVersion(String version) {
		switchVersionSelect.click();
		new Select(switchVersionSelect).selectByVisibleText(version);
	}

	public GroceryAddPage pressAddCustumerButton() {
		addCustumerBtn.click();

		return new GroceryAddPage(driver);
	}

	public void searchName(String customer) {
		searchBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));
		searchField.sendKeys(customer + Keys.ENTER);
		
		sleep(1000);
		
		WebElement table = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("grocery-crud-table")));
		WebElement searchedField = table.findElement(By.xpath("./tbody/tr[1]/td[3]"));
		assertThat(searchedField.getText(), is(customer));
	}

	public void selectAllCustomers() {
		selectAllCheck.click();
	}

	public DeleteDialog deleteSelectedCustomers() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement delete = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("delete-selected-button")));
		delete.click();
		return new DeleteDialog(driver);
	}

	public void verifyIfUsingDefaultTheme() {
		assertThat(formatUrl(driver.getCurrentUrl()), is("https://www.grocerycrud.com/demo/bootstrap_theme"));
	}

	public void verifyIfUsingV4Theme() {
		assertThat(formatUrl(driver.getCurrentUrl()), is("https://www.grocerycrud.com/demo/bootstrap_theme_v4"));
	}

	public void verifySucessfulyDeletedMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement deleteAlert = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
		String message = deleteAlert.findElement(By.xpath("./span[@data-growl='message']/p")).getText();

		assertThat(message.startsWith("Your data has been successfully deleted from the database."), is(true));
	}

}
