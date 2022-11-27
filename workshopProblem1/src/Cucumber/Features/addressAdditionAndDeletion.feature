Feature: adding and deleting an address

  Scenario: add an address and subsequently delete the address
    Given an existing user logged in
    And redirected to addresses
    And there exists at least one defined address
    When clicked button to create a new address
    And the required fields are filled
    And submitted
    Then the new address is displayed

