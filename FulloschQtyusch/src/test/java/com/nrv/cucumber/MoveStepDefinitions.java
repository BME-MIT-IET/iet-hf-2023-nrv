package com.nrv.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Virologist;
import model.codes.BlockCode;
import model.map.Field;
import model.map.Laboratory;
import org.junit.jupiter.api.Assertions;


public class MoveStepDefinitions {
  private Virologist virologist;
  private Field field;
  private Laboratory laboratory;

  @Given("virologist is created")
  public void virologist_is_created(){
    virologist = new Virologist();
  }
  @And("virologist stays on field")
  public void virologist_stys_on_field(){
    field = new Field();
    field.addVirologist(virologist);
  }
  @And("field has neighbour laboratory")
  public void field_has_neighbour_laboratory(){
    laboratory = new Laboratory(new BlockCode());
    field.addNeighbour(laboratory);
  }
  @When("virologist moves to laboratory")
  public void virologist_moves_to_laboratory(){
    virologist.move(laboratory);
  }
  @Then("virologist should stay on laboratory")
  public void virologist_should_stay_on_laboratory(){
    Assertions.assertEquals(laboratory, virologist.getField());
  }
}
