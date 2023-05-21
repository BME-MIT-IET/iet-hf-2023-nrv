package com.nrv.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Virologist;
import model.equipments.Axe;
import model.equipments.Bag;
import model.equipments.Cloak;
import model.equipments.Equipment;
import model.equipments.Glove;
import model.map.Material;
import model.map.Shelter;
import model.map.Warehouse;
import org.junit.jupiter.api.Assertions;

public class CollectStepDefinitions{
  VirologistStepDefinitions virologistCreator = new VirologistStepDefinitions();
  Shelter shelter;
  Equipment equipment;

  @Given("virologist stays on warehouse")
  public void virologistStaysOn(){
    Warehouse warehouse = new Warehouse();
    warehouse.AddVirologist(virologistCreator.getVirologist());
  }
  @When("virologist takes equipment")
  public void virologistEquips(){
    virologistCreator.getVirologist().Equip();
  }
  @When("virologist collects {word}")
  public void virologistCollects(String materialType){
    virologistCreator.getVirologist().Collect(Material.AMINO_ACID);
  }
  @Then("virologist should have more material than {int}")
  public void virologistShouldHaveMaterial(int num){
    if(virologistCreator.getVirologist().GetNucleotide() == 0){
      Assertions.assertTrue(virologistCreator.getVirologist().GetAminoAcid() > num);
    }else{
      Assertions.assertTrue(virologistCreator.getVirologist().GetNucleotide() > num);
    }
  }
  @Given("virologist stays on shelter")
  public void virologistStaysOnShelter() {
    shelter.AddVirologist(virologistCreator.getVirologist());
  }

  @And("shelter has equipment")
  public void shelterHasEquipment() {
    shelter = new Shelter(equipment);
  }

  @Then("virologist should have the equipment")
  public void virologistShouldHave() {
    Assertions.assertEquals(equipment,virologistCreator.getVirologist().GetEquipments().get(0));
    Assertions.assertEquals(1,virologistCreator.getVirologist().GetEquipments().size());
  }
  @Then("virologist should not have the equipment")
  public void virologistShouldNotHave() {
    Assertions.assertFalse(virologistCreator.getVirologist().GetEquipments().contains(equipment));
  }

  @Given("there is an equipment {word}")
  public void thereIsAnEquipment(String equipmentType) {
    switch (equipmentType) {
      case "axe" -> equipment = new Axe();
      case "bag" -> equipment = new Bag();
      case "cloak" -> equipment = new Cloak();
      case "glove" -> equipment = new Glove();
    }
  }

  @And("virologist has {int} equipments")
  public void virologistHasEquipments(int num) {
    virologistCreator.getVirologist().GetEquipments().clear();
    for(int i=0; i< num; ++i){
      virologistCreator.getVirologist().AddEquipment(new Bag());
    }
  }
}
