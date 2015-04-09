Feature: Logging into Salesforce

In order to work
As a sales person
I want to login

Scenario Outline: Logging in salesforce

Given I go to "loginURL" on "<Browser>"
And I enter "loginusername" as "<Username>"
And I enter "loginpassword" as "<Password>"
And I click on "loginButton"
Then login should be "<Expected_Result>"

	Examples:
	| Browser     | Username 				| Password 		| Expected_Result |
	| Mozilla  	  | provide username		| provide pass	| Failure 		  |
	| Mozilla  	  | xxxx	 				| zxzxzx		| success 		  |
	| Chrome	  | xxxx					| Azzzzz		| Failure		  |
	| Chrome	  | provide username		| provide pass	| success		  |
	
	
	
	