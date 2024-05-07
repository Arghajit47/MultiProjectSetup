
@Assessment
Feature: Assessment Testing
  @DynamicTable
  Scenario: DynamicTable Testing
    Given Visit the Website
    And Says we visited
    When Click on Table Data Button
    And We wait for 5 seconds
    Then Load the json text from "testData.json"
    And We input the file data
    And We click on the refreshTable Button
    And We verify the newly updated table data from "testData.json"
    And We close the browser