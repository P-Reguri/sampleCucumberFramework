package com.mysample.salesforce.testcases;

import com.mysample.salesforce.utils.TestBase;

import cucumber.api.java.en.And;

public class CommonUtils 
{
	TestBase selenium = TestBase.getInstance();
	
	//Click on the object
	@And("^I click on \"([^\"]*)\"$")
	public void I_Click_On(String object)
	{
		selenium.click(object);
	}
	
	@And("^I enter \"([^\"]*)\" as \"([^\"]*)\"$")
	public void I_Enter(String object, String text)
	{
		System.out.println("Entering in " + object + " value " + text);
		selenium.type(text, object);
	}

}
