Feature: Ui Testing Playground elements test
  Scenario: Testing load delay button
    Given I am on the uitestingplayground page
    When I click on the Load Delay button
    Then I should see the text button

  Scenario: Testing Progress Bar
    Given I am on the uitestingplayground page
    When I click on the Progress Bar button
    Then I should see the progress bar complete

    Scenario: Testing alert confirm and prompt
    Given I am on the uitestingplayground page
    When I click on the Alerts button
    Then I click alert and closed the alert
    Then I click confirm and closed the confirm
    Then I click prompt and closed the prompt

