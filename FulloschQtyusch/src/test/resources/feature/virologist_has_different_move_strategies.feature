Feature: Virologist has different move strategies
  Virologist can move normally (default), randomly (bear), or cannot move

  Example: Virologist moves normally as default
    Given virologist is created
    And virologist stays on field
    And field has neighbour laboratory
    When virologist moves to laboratory
    Then virologist should stay on laboratory

  Example: Virologist cannot move if infected with stun agent
    Given virologist is created
    And virologist stays on field
    And field has neighbour laboratory
    And virologist is infected with stun agent
    When virologist moves to laboratory
    Then virologist should stay on field

  Example: Virologist moves normally if infected with another agent (not bear or stun)
    Given virologist is created
    And virologist stays on field
    And field has neighbour laboratory
    And virologist is infected with forget agent
    When virologist moves to laboratory
    Then virologist should stay on laboratory