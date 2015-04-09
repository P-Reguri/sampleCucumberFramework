package com.mysample.salesforce.testcases;

import java.util.List;








import org.junit.Assert;

import com.mysample.salesforce.utils.TestBase;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ClickOnTabsTest 
{
	TestBase selenium = TestBase.getInstance();
	
	@Given("^I am logged in \"([^\"]*)\"$")
	public void I_am_logged_in(String browser)
	{
		//check if logged in the browser
		selenium.openBrowser(browser);
		if(!selenium.isLoggedIn())
		{
			selenium.doDefaultLogin();
		}
	}
	
	@Then("^All tabs on menu should be present$")
	public void All_Tabs_On_Menu_Should_Be_Present(DataTable table)
	{
		
		List<String> names = table.asList(String.class);
		
		for(int i=0; i<names.size(); i++)
		{
			selenium.log("Checking the tabs are presrent - " +names.get(i));
			Assert.assertTrue(selenium.isElementPresent(names.get(i)));
		}
	}
	
	@And("^\"([^\"]*)\" element should be present$")
	public void element_Should_Present(String object)
	{
		Assert.assertTrue("Object not found "+ object, selenium.isElementPresent(object) );
	}
	
}
