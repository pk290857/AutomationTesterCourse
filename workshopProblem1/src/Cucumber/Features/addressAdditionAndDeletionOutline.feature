Feature: adding and deleting an address

  Scenario Outline: add an address and subsequently delete the address
    Given an existing user logged in
    And redirected to addresses
    And there exists at least one defined address
    When clicked button to create a new address
    And the required fields are filled with <alias> and <address> and <city> and <zip> and <phoneNumber>
    And submitted
    Then the new address is displayed
    When deleting the new address
    Then the new address is deleted
    Examples:
      | alias       | address        | city     | zip     | phoneNumber   |
      | Guie1971    | 84 Manor Close | DIDSBURY | M20 0YB | 070 5124 9699 |


