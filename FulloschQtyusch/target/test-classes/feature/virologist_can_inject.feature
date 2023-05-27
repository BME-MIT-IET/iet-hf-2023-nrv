Feature: Virologist can inject another virologist with genetic code
  Genetic codes are learned by the virologist
  The virologist must have enough material (amino acid and nucleotide) to create an agent from genetic code
  Applied agents have effects on the virologist

  @Inject
  Example: Virologist can inject themselves with block agent
  It is reasonable for defense against other virologists
    Given virologist is created
    And virologist is infected with block agent
    When virologist is infected with stun agent
    Then virologist can still move

  @Inject
  Example: Virologist can inject another virologist with forget agent
  This will forget all learned genetic codes
    Given virologist is created
    And the injectable virologist is created
    And virologist 1 has enough material for forget genetic code
    And virologist 2 already learned some codes
    When virologist 1 injects virologist 2 with forget code
    Then virologist 2 knows no more code

    @Inject
    Example: Virologist cannot inject if they don't have enough material for the code
      Sad story
      They don't forget anything when no material is present to create forget agent from forget code
      Given virologist is created
      And virologist 1 already learned some codes
      And virologist 1 already learned forget code
      And virologist 1 does not have enough material for forget code
      When virologist 1 injects virologist 1 with forget code
      Then virologist 1 will still know forget code
      Then virologist 1 will still know some codes