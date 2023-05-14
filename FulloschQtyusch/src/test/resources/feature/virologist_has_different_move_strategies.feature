Feature: Virologist has different move strategies
  Virologist can move normally (default), randomly (bear), or cannot move

  Example: Virologist moves normally as default
    Given virologist is created
    And virologist stays on field
    And field has neighbour laboratory
    When virologist moves to laboratory
    Then virologist should stay on laboratory