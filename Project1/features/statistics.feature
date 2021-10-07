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
@statistics
Feature: accessing statistics

  @gotostatistics
  Scenario: the user accesses the statistics page
    Given the user is on the manager home page
    When I click statistics
    Then the user is forwarded to the statistics page

  @leavestatistics
  Scenario: the user returns to the manager home page
    Given the user is on the statistics page
    When I click manager home
    Then the user is sent to the manager home page
