@opener's
Feature: Opening browser

  Scenario: Open browser
    Given I open browser "chrome"
    When I moved to the main page
    Then Search field is visible