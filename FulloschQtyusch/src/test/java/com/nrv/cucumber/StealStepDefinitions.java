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
      virologistProvider.getVirologist().addEquipment(equipment);
    }else{
      virologist.addEquipment(equipment);
    }
  }

  @When("virologist {int} loots equipment")
  public void virologistLootsEquipment(int viroNum) {
    if(viroNum == 1){
      virologistProvider.getVirologist().lootEquipmentFrom(virologist);
    }else{
      virologist.lootEquipmentFrom(virologistProvider.getVirologist());
    }
  }

  @Then("virologist {int} has the equipment")
  public void virologistHasTheEquipment(int viroNum) {
    if(viroNum == 1){
      Assertions.assertTrue(virologistProvider.getVirologist().getEquipments().contains(equipment));
    }else{
      Assertions.assertTrue(virologist.getEquipments().contains(equipment));
    }
  }

  @Then("virologist {int} does not have the equipment")
  public void firstVirologistDoesNotHaveTheEquipment(int viroNum) {
    if(viroNum == 1){
      Assertions.assertFalse(virologistProvider.getVirologist().getEquipments().contains(equipment));
    }else{
      Assertions.assertFalse(virologist.getEquipments().contains(equipment));
    }
  }

  @And("game has no random factor")
  public void gameHasNoRandomFactor() {
    Game game = Game.create();
    game.randOn = false;
    virologist.setGame(game);
    virologistProvider.getVirologist().setGame(game);
  }
}
