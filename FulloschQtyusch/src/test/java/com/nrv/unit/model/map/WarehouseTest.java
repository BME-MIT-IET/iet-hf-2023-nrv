package com.nrv.unit.model.map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import model.Virologist;
import model.map.Material;
import model.map.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WarehouseTest {

  Warehouse warehouse;
  @BeforeEach
  void setUp() {
    warehouse = new Warehouse();
  }

  @Test
  void collectAminoTest() {
    Virologist mockVirologist = mock(Virologist.class);
    warehouse.collectMaterial(mockVirologist, Material.AMINO_ACID);
    verify(mockVirologist, times(1)).addAminoAcid(5);
  }
  @Test
  void collectNucleoTest() {
    Virologist mockVirologist = mock(Virologist.class);
    warehouse.collectMaterial(mockVirologist, Material.NUCLEOTIDE);
    verify(mockVirologist, times(1)).addNucleotide(5);
  }

  @Test
  void destroyMaterialTest() {
    Virologist mockVirologist = mock(Virologist.class);
    warehouse.destroyMaterial();
    verify(mockVirologist, times(0)).addNucleotide(anyInt());
    verify(mockVirologist, times(0)).addAminoAcid(anyInt());
  }
}