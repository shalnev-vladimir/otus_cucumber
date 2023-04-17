@searching_for_course_by_name
Feature: Searching for a specified course

  Scenario: Searching for a specified course on the Main Page and click on course title
    Given I open browser "chrome"
    When I moved to the main page
    And Course cards are displayed
    Then I click on the "Apache Kafka" course title