Feature: redbus bus booking
  Scenario Outline: Searching bus to Bangalore
    Given I am on the redbus Bangalore bus search page
    When I enter "<source>" in the source field
    And I enter "<destination>" in the destination field
    And I enter the date in the date field
    And I select booking for women only
    And I click on the search button
    Then I should see the list of buses

    Examples:
      | source       | destination        |
      | Manuthy pass | Central Silk Board |
      | Erode Bypass | Madiwala           |
      | Gandhipuram  | Madiwala           |