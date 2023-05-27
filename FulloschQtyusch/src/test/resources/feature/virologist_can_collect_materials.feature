Feature: Virologist can collect materials

  @Collect
  Example: Virologist can collect amino acid or nucleotide on warehouse
    Given virologist is created
    And virologist stays on warehouse
    When virologist collects amino
    Then virologist should have more material than 0