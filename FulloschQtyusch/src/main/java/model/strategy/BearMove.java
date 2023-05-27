package model.strategy;

import model.Virologist;
import model.agents.Bear;
import model.map.Field;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BearMove implements IMoveStr{
    @Override
    public void move(Virologist v, Field from, Field to) {

        List<Field> neighbours = from.getNeighbours();
        from.removeVirologist(v);
        Field randomNeighbour;

        randomNeighbour = neighbours.get(ThreadLocalRandom.current().nextInt(0, neighbours.size()));

        randomNeighbour.addVirologist(v);
        randomNeighbour.destroyMaterial();
        for (Virologist vir : randomNeighbour.getVirologists()) {
            vir.targetedWith(new Bear());
        }
        v.decreaseActions();
    }
}
