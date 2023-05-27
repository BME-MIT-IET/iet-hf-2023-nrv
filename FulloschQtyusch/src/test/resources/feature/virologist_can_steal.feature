Feature: Virologists can steal from each other
  @Steal
  Example: Normally a virologist cannot steal an equipment from another virologist
    Given virologist is created
    And another virologist is created
    And game has no random factor
    And virologist 1 has equipment axe
    When virologist 2 loots equipment
    Then virologist 1 has the equipment
    Then virologist 2 does not have the equipment

  @Steal
  Example: A virologist can steal an equipment from another virologist if that is stunned
    Given virologist is created
    And virologist is infected with stun agent
    And another virologist is created
    And game has no random factor
    And virologist 1 has equipment axe
    When virologist 2 loots equipment
    Then virologist 2 has the equipment
    Then virologist 1 does not have the equipment