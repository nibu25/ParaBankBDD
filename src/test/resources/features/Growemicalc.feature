Feature: Groww EMI calculator

  Scenario Outline: Calculate EMI for different loan details
    Given I am on the Groww EMI calculator page
    When I enter "<loanAmount>" in the loan amount field
    And I enter "<rate>" in the rate of interest field
    And I enter "<tenure>" in the loan tenure field
    Then I should see the EMI details as monthly EMI "<emi>", principal amount "<loanAmount>", total interest "<interest>" and total amount "<totalAmount>"

    Examples:
      | loanAmount | rate | tenure | emi  | interest | totalAmount |
      | 300000     | 7.5  | 10     | 3561 | 127326   | 427326      |