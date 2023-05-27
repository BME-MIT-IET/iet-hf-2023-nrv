package com.nrv.unit.model.map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import model.Virologist;
import model.codes.GeneticCode;
import model.map.Laboratory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LaboratoryTest {
  @Mock
  GeneticCode code;
  @InjectMocks
  Laboratory labor;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void learnGeneticCodeTest() {
    Virologist mockVirologist = mock(Virologist.class);
    labor.learnGeneticCode(mockVirologist);
    verify(mockVirologist, times(1)).addGeneticCode(code);
  }
}