package com.sicredi.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteDialog {
	
	private WebDriver driver;
	private WebElement deleteModal;

	public DeleteDialog(WebDriver driver) {
		this.driver = driver;
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		deleteModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("delete-multiple-confirmation")));
		WebElement deleteTitle = deleteModal.findElement(By.className("modal-title"));
		assertThat(deleteTitle.getText(), is("Delete"));
	}

	public GroceryCrudPage confirmDelete() {
		deleteModal.findElement(By.className("delete-multiple-confirmation-button")).click();
		return new GroceryCrudPage(driver);
	}

	public void verifyDeleteMessageForOneCustomer() {
		WebElement deleteMsg = deleteModal.findElement(By.className("alert-delete-multiple-one"));
		assertThat(deleteMsg.getText(), is("Are you sure that you want to delete this 1 item?"));
	}

}
