package model.map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import model.Virologist;
import model.codes.ForgetCode;
import model.codes.StunCode;
import model.equipments.Equipment;
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
    field.AddVirologist(new Virologist());
    field.AddVirologist(new Virologist());
    Virologist mockVirologist = mock(Virologist.class);
    verify(mockVirologist, times(1)).SetField(field);
    field.AddVirologist(mockVirologist);
    Assertions.assertFalse(field.GetVirologists().isEmpty());
    Assertions.assertEquals(3, field.GetVirologists().size());
    Assertions.assertTrue(field.GetVirologists().contains(mockVirologist));
  }

  @Test
  void neighboursTest() {
    Assertions.assertTrue(field.GetNeighbours().isEmpty());
    Field n1 = new Field();
    Laboratory n2 = new Laboratory(new ForgetCode());
    InfectedLaboratory n3 = new InfectedLaboratory(new StunCode());
    Field notNeighbour = new Field();
    field.AddNeighbour(n1);
    field.AddNeighbour(n2);
    field.AddNeighbour(n3);
    Assertions.assertTrue(field.GetNeighbours().contains(n1));
    Assertions.assertTrue(n1.GetNeighbours().contains(field));
    Assertions.assertTrue(field.GetNeighbours().contains(n2));
    Assertions.assertTrue(n2.GetNeighbours().contains(field));
    Assertions.assertTrue(field.GetNeighbours().contains(n3));
    Assertions.assertTrue(n3.GetNeighbours().contains(field));
    Assertions.assertFalse(notNeighbour.GetNeighbours().contains(field));
  }

  @Test
  void removeVirologistTest() {
    Virologist mockVirologist = mock(Virologist.class);
    Assertions.assertFalse(field.GetVirologists().contains(mockVirologist));
    field.AddVirologist(mockVirologist);
    verify(mockVirologist, times(1)).SetField(field);
    Assertions.assertTrue(field.GetVirologists().contains(mockVirologist));
    field.RemoveVirologist(mockVirologist);
    Assertions.assertFalse(field.GetVirologists().contains(mockVirologist));
  }

  @Test
  void learnGeneticCodeTest() {
    Virologist mockVirologist = mock(Virologist.class);
    field.LearnGeneticCode(mockVirologist);
    verify(mockVirologist, times(0)).Learn();
  }

  @Test
  void collectMaterialOnFieldTest() {
    Virologist mockVirologist = mock(Virologist.class);
    field.CollectMaterial(mockVirologist);
    verify(mockVirologist, times(0)).AddNucleotide(anyInt());
    verify(mockVirologist, times(0)).AddAminoAcid(anyInt());
  }

  @Test
  void pickUpEquipmentTest() {
    Virologist mockVirologist = mock(Virologist.class);
    Equipment equipment = mock(Equipment.class);
    field.Drop(equipment);
    field.PickUpEquipment(mockVirologist);
    verify(mockVirologist, times(1)).AddEquipment(equipment);
  }
}