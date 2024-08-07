@api
Feature: API - Paczkomaty Export

  Scenario Outline: Export list of Paczkomaty for given City
    When I request for a list of Paczkomaty in <city> city
    Then Response status code is 200
    And I export the Paczkomaty list to the paczkomaty.<city>.json file

    Examples:
      | city           |
      | Gdynia         |
      | Kraków         |
      | Świętochłowice |