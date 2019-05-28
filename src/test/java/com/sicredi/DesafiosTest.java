package com.sicredi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sicredi.pages.DeleteDialog;
import com.sicredi.pages.GroceryCrudPage;

public class DesafiosTest extends JUnitTestBase {

	@Before
	public void setUp() {
		driver.manage().window().maximize();
		driver.get("https://www.grocerycrud.com/demo/bootstrap_theme");
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void testDesafio1() {
		createCustomer();
	}

	@Test
	public void testDesafio2() {
		GroceryAddPage addPage = createCustomer();

		GroceryCrudPage groceryCrudPage = addPage.clickAtGoBack();
		groceryCrudPage.verifyIfUsingV4Theme();

		groceryCrudPage.searchName("Teste Sicredi");
		groceryCrudPage.selectAllCustomers();

		DeleteDialog deleteDialog = groceryCrudPage.deleteSelectedCustomers();
		deleteDialog.verifyDeleteMessageForOneCustomer();

		groceryCrudPage = deleteDialog.confirmDelete();
		groceryCrudPage.verifySucessfulyDeletedMessage();
	}

	private GroceryAddPage createCustomer() {
		GroceryCrudPage groceryCrudPage = new GroceryCrudPage(driver);
		groceryCrudPage.verifyIfUsingDefaultTheme();

		groceryCrudPage.changeVersion("Bootstrap V4 Theme");
		groceryCrudPage.verifyIfUsingV4Theme();

		GroceryAddPage addPage = groceryCrudPage.pressAddCustumerButton();
		addPage.fillName("Teste Sicredi", "Teste");
		addPage.fillFirstContact("João Paulo", "51 9999-9999");
		addPage.fillAddress("Av Assis Brasil, 3970", "Torre D", "Porto Alegre", "RS", "91000-000", "Brasil");
		addPage.fillEmployeer("Fixter");
		addPage.fillCreditLimit(200);
		addPage.pressSaveButton();
		addPage.verifySucessfullyMessageIsShowed();

		return addPage;
	}
}