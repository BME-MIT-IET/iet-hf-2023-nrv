Feature: virologist can collect different equipments

  @Equip
  Example: Virologist can collect an axe on a shelter
    Given there is an equipment axe
    And virologist is created
    And shelter has equipment
    And virologist stays on shelter
    When virologist takes equipment
    Then virologist should have the equipment

  @Equip
  Example: Virologist cannot collect anything if injected with stun
    Given there is an equipment axe
    And virologist is created
    And shelter has equipment
    And virologist stays on shelter
    And virologist is infected with stun agent
    When virologist takes equipment
    Then virologist should not have the equipment

  @Equip
  Example: Virologist can have limited equipments
      Given virologist is created
      And virologist has 3 equipments
      And there is an equipment bag
      And shelter has equipment
      And virologist stays on shelter
      When virologist takes equipment
      Then virologist should not have the equipment