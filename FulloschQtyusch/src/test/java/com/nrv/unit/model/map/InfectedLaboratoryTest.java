package com.nrv.unit.model.map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import model.Virologist;
import model.agents.Bear;
import model.codes.GeneticCode;
import model.map.InfectedLaboratory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InfectedLaboratoryTest {

  InfectedLaboratory iLabor;
  @BeforeEach
  void setUp() {
    iLabor = new InfectedLaboratory(mock(GeneticCode.class));
  }

  @Test
  void addVirologist() {
    Virologist mockVirologist = mock(Virologist.class);
    iLabor.addVirologist(mockVirologist);
    verify(mockVirologist, times(1)).targetedWith(any(Bear.class));
  }
}