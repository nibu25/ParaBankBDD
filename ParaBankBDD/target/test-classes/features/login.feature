Feature: ParaBank Login

  @smoke
  Scenario: Successful login with valid credentials and verify title
    Given User is on the Parabank login page
    When User enters valid username and password
    And User clicks on the login button
    Then User should be redirected to the account overview page
    And The page title should be "ParaBank | Accounts Overview"
