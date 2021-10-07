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
@logout
Feature: logging out

  @logoutemployee
  Scenario: Employee logging out
    Given the user is on the employee home page
    When I click logout
    Then I am redirected to the login page

  @logoutmanager
  Scenario: Manager logging out
    Given the user is on the manager home page
    When I click logout
    Then I am redirected to the login page
    
  @logoutstatistics
  Scenario: Logging out from statistics
  	Given the user is on the statistics page
  	When I click logout
  	Then I am redirected to the login page
