package com.nrv.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Game;
import model.Virologist;
import model.codes.BlockCode;
import model.equipments.Axe;
import model.map.Field;
import org.junit.jupiter.api.Assertions;

public class AttackStepDefinitions {
  Virologist virologist;

  Game game;
  VirologistStepDefinitions virologistProvider = new VirologistStepDefinitions();
  @And("virologist {int} has axe")
  public void virologistHasAxe(int viroNum) {
    if(viroNum == 1 ){
      virologistProvider.getVirologist().addEquipment(new Axe());
    }else{
      virologist.addEquipment(new Axe());
    }
  }

  @When("virologist {int} attacks")
  public void virologistAttacks(int viroNum) {
    if(viroNum == 1 ){
      virologistProvider.getVirologist().attack(virologist);
    }else{
      virologist.attack(virologistProvider.getVirologist());
    }
  }

  @When("virologist attacks themselves")
  public void virologistAttacksThemselves() {
    virologistProvider.getVirologist().attack(virologistProvider.getVirologist());
  }

  @Then("virologist {int} is killed")
  public void virologistIsKilled(int viroNum) {
    Virologist v;
    if(viroNum == 1){
      v=virologistProvider.getVirologist();
    }else{
      v= virologist;
    }
    Assertions.assertFalse(v.getField().getVirologists().contains(v));
  }

  @And("virologist {int} is in the game")
  public void virologistIsInAGame(int viroNum) {
    if(viroNum == 1){
      game.addVirologist(virologistProvider.getVirologist());
      virologistProvider.getVirologist().setGame(game);
    }
    else{
      game.addVirologist(virologist);
      virologist.setGame(game);
    }
  }

  @And("there is a game")
  public void thereIsAGame() {
    game = Game.create();
    game.addGeneticCode(new BlockCode());
  }

  @And("victim virologist is created")
  public void victimVirologistIsCreated() {
    virologist = new Virologist();
    virologist.setField(new Field());
  }
}
