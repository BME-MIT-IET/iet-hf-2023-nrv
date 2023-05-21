Feature: Virologist can collect materials
Example: Virologist can collect amino acid or nucleotide on warehouse
  Given virologist is created
  And virologist stays on warehouse
  When virologist collects
  Then virologist should have more material than 0