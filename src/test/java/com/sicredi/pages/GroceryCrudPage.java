package com.sicredi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.sicredi.GroceryAddPage;

/**
 * Sample page
 */
public class GroceryCrudPage extends Page {

	@FindBy(how = How.ID, using = "switch-version-select")
	private WebElement switchVersionSelect;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Add Customer")
	private WebElement addCustumerBtn;

	public GroceryCrudPage(WebDriver webDriver) {
		super(webDriver);
	}

	public void open() {
		driver.get("https://www.grocerycrud.com/demo/bootstrap_theme");
	}

	public void changeVersion(String version) {
		switchVersionSelect.click();
		new Select(switchVersionSelect).selectByVisibleText(version);
	}

	public GroceryAddPage pressAddCustumerButton() {
		addCustumerBtn.click();
		return new GroceryAddPage(driver);
	}

}
