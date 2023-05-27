package com.nrv.unit.model.map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import model.Virologist;
import model.codes.ForgetCode;
import model.codes.StunCode;
import model.equipments.Equipment;
import model.map.Field;
import model.map.InfectedLaboratory;
import model.map.Laboratory;
import model.map.Material;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldTest {
  Field field;

  @BeforeEach
  void setUp() {
    field = new Field();
  }

  @Test
  void virologistsOnFieldTest() {
    field.addVirologist(new Virologist());
    field.addVirologist(new Virologist());
    Virologist mockVirologist = mock(Virologist.class);
    field.addVirologist(mockVirologist);
    verify(mockVirologist, times(1)).setField(field);
    Assertions.assertFalse(field.getVirologists().isEmpty());
    Assertions.assertEquals(3, field.getVirologists().size());
    Assertions.assertTrue(field.getVirologists().contains(mockVirologist));
  }

  @Test
  void neighboursTest() {
    Assertions.assertTrue(field.getNeighbours().isEmpty());
    Field n1 = new Field();
    Laboratory n2 = new Laboratory(new ForgetCode());
    InfectedLaboratory n3 = new InfectedLaboratory(new StunCode());
    Field notNeighbour = new Field();
    field.addNeighbour(n1);
    field.addNeighbour(n2);
    field.addNeighbour(n3);
    Assertions.assertTrue(field.getNeighbours().contains(n1));
    Assertions.assertTrue(n1.getNeighbours().contains(field));
    Assertions.assertTrue(field.getNeighbours().contains(n2));
    Assertions.assertTrue(n2.getNeighbours().contains(field));
    Assertions.assertTrue(field.getNeighbours().contains(n3));
    Assertions.assertTrue(n3.getNeighbours().contains(field));
    Assertions.assertFalse(notNeighbour.getNeighbours().contains(field));
  }

  @Test
  void removeVirologistTest() {
    Virologist mockVirologist = mock(Virologist.class);
    Assertions.assertFalse(field.getVirologists().contains(mockVirologist));
    field.addVirologist(mockVirologist);
    verify(mockVirologist, times(1)).setField(field);
    Assertions.assertTrue(field.getVirologists().contains(mockVirologist));
    field.removeVirologist(mockVirologist);
    Assertions.assertFalse(field.getVirologists().contains(mockVirologist));
  }

  @Test
  void learnGeneticCodeTest() {
    Virologist mockVirologist = mock(Virologist.class);
    field.learnGeneticCode(mockVirologist);
    verify(mockVirologist, times(0)).learn();
  }

  @Test
  void collectMaterialOnFieldTest() {
    Virologist mockVirologist = mock(Virologist.class);
    field.collectMaterial(mockVirologist, Material.GENERIC);
    verify(mockVirologist, times(0)).addNucleotide(anyInt());
    verify(mockVirologist, times(0)).addAminoAcid(anyInt());
  }

  @Test
  void pickUpEquipmentTest() {
    Virologist mockVirologist = mock(Virologist.class);
    Equipment equipment = mock(Equipment.class);
    field.drop(equipment);
    field.pickUpEquipment(mockVirologist);
    verify(mockVirologist, times(1)).addEquipment(equipment);
  }
}