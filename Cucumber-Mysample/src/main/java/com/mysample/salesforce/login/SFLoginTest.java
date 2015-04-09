package com.mysample.salesforce.login;

import org.junit.Assert;

import com.mysample.salesforce.utils.*;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SFLoginTest 
{
	TestBase selenium = TestBase.getInstance();
	
	
	@Given("^I go to \"([^\"]*)\" on \"([^\"]*)\"$")
	public void I_Go_To_Salesforce(String url, String browser)
	{
		System.out.println("I am going to " + url + " on "+ browser);
		selenium.openBrowser(browser);
		selenium.navigate(url);
	}
	
/*	@And("^I enter \"([^\"]*)\" as \"([^\"]*)\"$")
	public void I_Enter(String object, String text)
	{
		System.out.println("Entering in " + object + " value " + text);
		selenium.type(text, object);
	}
	
	@And("^I click on \"([^\"]*)\"$")
	public void I_Click(String object)
	{
		System.out.println("Clicking on " + object);
		selenium.click(object);
	}*/
	
	
	@Then("^login should be \"([^\"]*)\"$")
	public void Then_Login_Should_be(String expectedResult)
	{
		System.out.println("Login -- " + expectedResult);
		boolean result = selenium.isElementPresent("searchTextField"); 
		String actualResult=null;
		if(result)
			actualResult="success";
		else
			actualResult="Failure";
		
		Assert.assertEquals(expectedResult,	actualResult);
	}

}
