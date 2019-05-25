package com.sicredi;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.sicredi.pages.GroceryCrudPage;

public class DesafiosTest extends JUnitTestBase {

	private GroceryCrudPage groceryCrudPage;

	@Before
	public void initPageObjects() {
		groceryCrudPage = PageFactory.initElements(driver, GroceryCrudPage.class);
		driver.manage().window().maximize();
	}

	@Test
	public void testDesafio1() {
		createCustomer();
	}

	@Test
	public void testDesafio2() {
		GroceryAddPage addPage = createCustomer();
		
		addPage.clickAtGoBack();		
		assertThat(driver.getCurrentUrl(), is("https://www.grocerycrud.com/demo/bootstrap_theme_v4"));
		
		groceryCrudPage.searchName("Teste Sicredi");
		
		groceryCrudPage.selectAllCustomers();
		
		groceryCrudPage.deleteCustomers();
		assertThat(groceryCrudPage.getMessage().startsWith("Are you sure that you want to delete this 1 item?"), is(true));
		
		groceryCrudPage.confirmDelete();
		assertThat(groceryCrudPage.getMessage().startsWith("Your data has been successfully deleted from the database."), is(true));
		
		driver.close();
		
		
	}
	
	private GroceryAddPage createCustomer() {
		
		groceryCrudPage.open();
		assertThat(driver.getCurrentUrl(), is("https://www.grocerycrud.com/demo/bootstrap_theme"));

		groceryCrudPage.changeVersion("Bootstrap V4 Theme");
		assertThat(driver.getCurrentUrl(), is("https://www.grocerycrud.com/demo/bootstrap_theme_v4"));

		GroceryAddPage addPage = groceryCrudPage.pressAddCustumerButton();
		assertThat(driver.getCurrentUrl(), is("https://www.grocerycrud.com/demo/bootstrap_theme_v4/add"));
		

		addPage.fillName("Teste Sicredi", "Teste");
		addPage.fillFirstContact("João Paulo", "51 9999-9999");
		addPage.fillAddress("Av Assis Brasil, 3970", "Torre D", "Porto Alegre", "RS", "91000-000");
		addPage.fillEmployeer("Fixter");
		addPage.fillCreditLimit(200);
		       
		addPage.pressSaveButton();
		
		assertThat(addPage.getMessage().startsWith("Your data has been successfully stored into the database."), is(true));
		return addPage;
	}
}