package com.nrv.cucumber;

import io.cucumber.java.en.And;
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
import model.codes.ChoreaCode;
import model.codes.ForgetCode;
import model.codes.GeneticCode;
import model.codes.StunCode;
import model.map.Field;
import org.junit.jupiter.api.Assertions;

public class InjectStepDefinitions {
  VirologistStepDefinitions virologistProvider = new VirologistStepDefinitions();
  Virologist virologist;

  @Then("virologist can still move")
  public void virologistCanMove(){
    Virologist v = virologistProvider.getVirologist();
    Field before = new Field();
    Field after = new Field();
    before.addNeighbour(after);
    v.setField(before);
    v.move(after);
    Assertions.assertEquals(after, v.getField());
  }
  @And("virologist is infected with {word} agent")
  public void virologistIsInfectedWithBlockAgent(String agentType) {
    Agent agent = new Forget(1);
    switch (agentType){
      case "block" -> agent = new Block(1);
      case "bear" -> agent = new Bear(1);
      case "stun" -> agent = new Stun(1);
      case "chorea" -> agent = new Chorea(1);
    }
    virologistProvider.getVirologist().targetedWith(agent);
  }

  @And("the injectable virologist is created")
  public void theInjectableVirologistIsCreated() {
    virologist = new Virologist();
  }

  @And("virologist {int} has enough material for {word} genetic code")
  public void virologistHasEnoughMaterialForForgetGeneticCode(int virNum, String codeType) {
    GeneticCode code = getActualCode(codeType);
    Virologist v = getActualVirologist(virNum);
    v.addAminoAcid(code.getAminoAcidPrice());
    v.addNucleotide(code.getNucleotidePrice());
  }

  @And("virologist {int} already learned some codes")
  public void virologistAlreadyLearnedSomeCodes(int virNum) {
    Virologist v = getActualVirologist(virNum);
    v.addGeneticCode(new StunCode());
    v.addGeneticCode(new BlockCode());
    Assertions.assertFalse(v.getGeneticCodes().isEmpty());

  }

  @When("virologist {int} injects virologist {int} with {word} code")
  public void virologistInjectsVirologist(int vir1, int vir2, String codeType) {
    Virologist v1 = getActualVirologist(vir1);
    Virologist v2 = getActualVirologist(vir2);
    v1.inject(v2, getActualCode(codeType));
  }

  @Then("virologist {int} knows no more code")
  public void virologistKnowsNoMoreCode(int virNum) {
    Virologist v = getActualVirologist(virNum);
    Assertions.assertTrue(v.getGeneticCodes().isEmpty());
  }
  private Virologist getActualVirologist(int virNum){
    return virNum == 1 ? virologistProvider.getVirologist() : virologist;
  }
  private GeneticCode getActualCode(String codeType){
    GeneticCode code = new ForgetCode();
    switch (codeType){
      case "block" -> code = new BlockCode();
      case "stun" -> code = new StunCode();
      case "chorea" -> code = new ChoreaCode();
    }
    return code;
  }

  @And("virologist {int} already learned {word} code")
  public void virologistAlreadyLearnedForgetCode(int virNum, String codeType) {
    Virologist v = getActualVirologist(virNum);
    GeneticCode code = getActualCode(codeType);
    v.addGeneticCode(code);
  }

  @And("virologist {int} does not have enough material for {word} code")
  public void virologistDoesNotHaveEnoughMaterialForForgetCode(int viroNum, String codeType) {
    Virologist v = getActualVirologist(viroNum);
    try {
      v.removeAminoAcid(v.getAminoAcid());
      v.removeNucleotide(v.getNucleotide());
    }catch(Exception e){}
  }

  @Then("virologist {int} will still know forget code")
  public void itWillStillKnowForgetCode(int viroNum) {
    Virologist v = getActualVirologist(viroNum);
    Assertions.assertFalse(v
        .getGeneticCodes()
        .stream()
        .filter(c -> c instanceof  ForgetCode)
        .toList()
        .isEmpty()
    );
  }

  @Then("virologist {int} will still know some codes")
  public void itWillStillKnowSomeCodes(int viroNum) {
    Virologist v = getActualVirologist(viroNum);
    Assertions.assertFalse(v.getGeneticCodes().isEmpty());
  }
}
