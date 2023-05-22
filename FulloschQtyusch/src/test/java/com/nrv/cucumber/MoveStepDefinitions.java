package com.nrv.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Virologist;
import model.agents.Agent;
import model.agents.Bear;
import model.agents.Block;
import model.agents.Chorea;
import model.agents.Forget;
import model.agents.Stun;
import model.codes.BlockCode;
import model.map.Field;
import model.map.Laboratory;
import org.junit.jupiter.api.Assertions;


public class MoveStepDefinitions {
  private Field field;
  private Laboratory laboratory;

  private VirologistStepDefinitions virologistCreator = new VirologistStepDefinitions();
  @And("virologist stays on field")
  public void virologistStaysOnField(){
    field = new Field();
    field.AddVirologist(virologistCreator.getVirologist());
  }
  @And("field has neighbour laboratory")
  public void fieldHasNeighbourLaboratory(){
    laboratory = new Laboratory(new BlockCode());
    field.AddNeighbour(laboratory);
  }
  @When("virologist moves to laboratory")
  public void virologistMovesToLaboratory(){

    virologistCreator.getVirologist().Move(laboratory);
  }
  @Then("virologist should stay on {word}")
  public void virologistShouldStayOnLaboratory(String fieldType){
    Field expectedField = new Field();
    switch (fieldType) {
      case "field" -> expectedField = field;
      case "laboratory" -> expectedField = laboratory;
    }
    Assertions.assertEquals(expectedField, virologistCreator.getVirologist().getField());
  }
}
