@courses_start_after_or_on_given_date
Feature: Searching for a courses that starts after specified date

  Scenario: Searching for courses on the Main Page and print it's names and start dates
    Given I open browser "chrome"
    When I moved to the main page
    And Course cards are displayed
    Then I display information of them in the console