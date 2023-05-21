package com.nrv.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Game;
import model.Virologist;
import model.equipments.Axe;
import model.equipments.Equipment;
import org.junit.jupiter.api.Assertions;

public class StealStepDefinitions {

  Virologist virologist;
  Equipment equipment;
  VirologistStepDefinitions virologistProvider = new VirologistStepDefinitions();

  @And("another virologist is created")
  public void anotherVirologistIsCreated() {
    virologist = new Virologist();
  }

  @And("virologist {int} has equipment axe")
  public void firstVirologistHasEquipmentAxe(int virologistNumber) {
    equipment = new Axe();
    if(virologistNumber == 1){
      virologistProvider.getVirologist().AddEquipment(equipment);
    }else{
      virologist.AddEquipment(equipment);
    }
  }

  @When("virologist {int} loots equipment")
  public void virologistLootsEquipment(int viroNum) {
    if(viroNum == 1){
      virologistProvider.getVirologist().LootEquipmentFrom(virologist);
    }else{
      virologist.LootEquipmentFrom(virologistProvider.getVirologist());
    }
  }

  @Then("virologist {int} has the equipment")
  public void virologistHasTheEquipment(int viroNum) {
    if(viroNum == 1){
      Assertions.assertTrue(virologistProvider.getVirologist().GetEquipments().contains(equipment));
    }else{
      Assertions.assertTrue(virologist.GetEquipments().contains(equipment));
    }
  }

  @Then("virologist {int} does not have the equipment")
  public void firstVirologistDoesNotHaveTheEquipment(int viroNum) {
    if(viroNum == 1){
      Assertions.assertFalse(virologistProvider.getVirologist().GetEquipments().contains(equipment));
    }else{
      Assertions.assertFalse(virologist.GetEquipments().contains(equipment));
    }
  }

  @And("game has no random factor")
  public void gameHasNoRandomFactor() {
    Game game = Game.Create();
    game.randOn = false;
    virologist.SetGame(game);
    virologistProvider.getVirologist().SetGame(game);
  }
}
