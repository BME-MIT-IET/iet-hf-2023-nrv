Feature: Virologist can attack another virologist with an axe
  Example: Virologist can attack themselves if has axe
    Given virologist is created
    And there is a game
    And virologist 1 is in the game
    And virologist 1 has axe
    When virologist attacks themselves
    Then virologist 1 is killed

  Example: Virologist can attack another virologist
    This will die as default
    Given virologist is created
    And victim virologist is created
    And there is a game
    And virologist 1 is in the game
    And virologist 2 is in the game
    And virologist 1 has axe
    When virologist 1 attacks
    Then virologist 2 is killed

