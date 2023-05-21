package com.nrv.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import model.Virologist;
import model.agents.Agent;
import model.agents.Bear;
import model.agents.Block;
import model.agents.Chorea;
import model.agents.Forget;
import model.agents.Stun;

public class VirologistStepDefinitions {
  private static Virologist virologist;
  @Given("virologist is created")
  public void virologistIsCreated(){
    virologist = new Virologist();
  }
  public Virologist getVirologist(){
    return virologist;
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
    agent.ApplyStrategy(virologist);
  }

}
