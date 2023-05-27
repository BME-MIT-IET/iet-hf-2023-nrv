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
import model.map.Field;

public class VirologistStepDefinitions {
  private static Virologist virologist;
  @Given("virologist is created")
  public void virologistIsCreated(){
    virologist = new Virologist();
    virologist.setField(new Field());
  }
  public Virologist getVirologist(){
    return virologist;
  }

}
