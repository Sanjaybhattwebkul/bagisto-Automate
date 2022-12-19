package admin.Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import admin.pageobjects.SalesRepository;
import baseComponent.BaseTest;

public class SalesTest extends BaseTest{
	
	@Test(dependsOnGroups={"AdminLoginTest.login"})
	public void createShipping() throws IOException {
		getGlobalData();
		SalesRepository sales = new SalesRepository(driver);
		sales.completeOrderProcess();
	}
}
