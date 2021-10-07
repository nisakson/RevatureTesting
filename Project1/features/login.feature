#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@login
Feature: Logging in
	Background: 
  	Given the user is on the login page

  	@employeelogin
  	Scenario: Employee logging in with the correct credentials
   	 When the user inputs the correct username
    	And the user inputs the correct password
    	Then the user is forwarded to the employee home page
    
  	@managerlogin
  	Scenario: Manager logging in with the correct credentials
    	When the manager user inputs the correct username
    	And the manager user inputs the correct password
    	Then the user is forwarded to the manager home page

  	@loginfail
  	Scenario: Logging in with the wrong credentials
  	  When the user inputs the correct username
    	But the user inputs the incorrect password
    	Then I am redirected to the login page