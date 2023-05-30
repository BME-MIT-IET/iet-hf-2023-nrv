package com.nrv.unit.controller;

import static org.mockito.AdditionalAnswers.answerVoid;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import control.Controller;
import java.util.ArrayList;
import model.Game;
import model.Virologist;
import model.codes.GeneticCode;
import model.equipments.Bag;
import model.equipments.Equipment;
import model.map.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.Window;

public class ControllerTest {
  @Mock
  private Game game;
  @Mock
  private Window window;
  @InjectMocks
  private Controller controller = new Controller();

  @Mock
  private Virologist virologist;

  @Mock
  private Field field;
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void successfulAttackTest(){
    ArrayList<Virologist> virologistsInGame = new ArrayList<Virologist>(2);
    virologistsInGame.add(virologist);
    ArrayList<Virologist> virologistsInGameAfterKill = new ArrayList<Virologist>(2);
    when(game.GetCurrentPlayer()).thenReturn(virologist);
    when(virologist.getActionCount()).thenReturn(1);
    when(virologist.getName()).thenReturn("TestVirologist");
    when(game.getVirologists()).thenReturn(virologistsInGame)
        .thenReturn(virologistsInGameAfterKill);
    controller.attack(virologist);
    String actionResult = controller.getActionMessage();
    Assertions.assertTrue(actionResult.contains("killed"));
  }
  @Test
  void dropTest(){
    ArrayList<Equipment> equipments = new ArrayList<>();
    equipments.add(new Bag());
    ArrayList<Equipment> equipmentsAfterDrop = new ArrayList<>();
    when(game.GetCurrentPlayer()).thenReturn(virologist);
    when(virologist.getActionCount()).thenReturn(1);
    when(virologist.getName()).thenReturn("TestVirologist");
    when(virologist.GetEquipments()).thenReturn(equipments).thenReturn(equipmentsAfterDrop);
    controller.drop();
    String actionResult = controller.getActionMessage();
    Assertions.assertTrue(actionResult.contains("dropped"));
  }
  @Mock
  GeneticCode code;
  @Test
  void noInjectTest(){
    when(game.GetCurrentPlayer()).thenReturn(virologist);
    when(virologist.getActionCount()).thenReturn(1);
    when(virologist.getName()).thenReturn("TestVirologist");
    when(code.getName()).thenReturn("TestCode");
    doThrow(new RuntimeException("Failure")).when(virologist).Inject(virologist, code);
    controller.inject(virologist, code);
    String actionResult = controller.getActionMessage();
    Assertions.assertEquals("Failure", actionResult);
  }

}
