package control;

import model.Game;

import model.Virologist;
import model.codes.GeneticCode;
import model.map.*;
import model.Subject;
import view.Window;

/**
 * Prototípus külvilággal való kommunikációjáért felelős osztály.
 * Megvalósítja a dokumentációban leírt bemeneti nyelv funkcióit, valamint közvetít a modell és a felhasználó(k) között.
 */
public class Controller extends Subject {

    /**
     * Az aktuálisan játszott játék.
     */
    private final Game game;
    private final Window window;
    private String actionMessage;

    public Controller(Game game) {
        actionMessage = "";
        this.game = game;
        window = new Window(this, game);
        attach(window);
        Virologist first = game.getCurrentPlayer();
        first.attach(window);
    }

    /**
     * Virológus megtámadása
     * @param v Megtámadott virológus
     */
    public void attack(Virologist v){
        Virologist currentPlayer = game.getCurrentPlayer();
        actionMessage = v.getName() + " might be dead by now▄";
        currentPlayer.attack(v);
        if(!currentPlayer.equals(game.getCurrentPlayer())){
            currentPlayer.detach(window);
            currentPlayer = game.getCurrentPlayer();
            currentPlayer.attach(window);
            actionMessage = "My turn...";
            notifyAllObservers();
        }
    }

    /**
     * Virológus mozgatása
     * @param f Cél mező
     */
    public void move(Field f){
        Virologist currentPlayer = game.getCurrentPlayer();
        actionMessage =  "Trying to move to " + f.getName() + "...";
        currentPlayer.move(f);

    }

    /**
     * Virológus felszerelésének eldobása
     */
    public void drop(){
        Virologist currentPlayer = game.getCurrentPlayer();
        actionMessage = "Trying to drop an equipmnet...";
        currentPlayer.drop();


    }

    /**
     * Virológus aminosav lopása
     * @param v Célpont virológus
     */
    public void lootAminoFrom(Virologist v){
        Virologist currentPlayer = game.getCurrentPlayer();
        actionMessage = "Trying to loot amino acid form " + v.getName() + "...";
        currentPlayer.lootAminoAcidFrom(v);


    }

    /**
     * Virológus nukleotid lopása
     * @param v Célpont virológus
     */
    public void lootNucleoFrom(Virologist v){
        Virologist currentPlayer = game.getCurrentPlayer();
        actionMessage = "Trying to nucleotide acid form " + v.getName() + "...";
        currentPlayer.lootNucleotideFrom(v);


    }

    /**
     * Virológus felszerelés lopása
     * @param v Célpont virológus
     */
    public void lootEquipmentFrom(Virologist v){
        Virologist currentPlayer = game.getCurrentPlayer();
        actionMessage = "Trying to equipment acid form " + v.getName() + "...";
        currentPlayer.lootEquipmentFrom(v);


    }

    /**
     * Virológus anyag gyűjtése
     */
    public void collect(){
        Virologist currentPlayer = game.getCurrentPlayer();
        actionMessage = "Trying to collect material...";
        currentPlayer.collect();

    }

    /**
     * Virológus genetikai kód tanulása
     */
    public void learn(){
        Virologist currentPlayer = game.getCurrentPlayer();
        actionMessage = "Trying to learn a genetic code...";
        currentPlayer.learn();

    }

    /**
     * Virológus felszerelés felvétele
     */
    public void equip(){
        Virologist currentPlayer = game.getCurrentPlayer();
        actionMessage = "Trying to equip an equipment...";
        currentPlayer.equip();

    }

    /**
     * Virológus injektálása
     * @param v Célpont virológus
     * @param code Injektáláshoz szükséges genetikai kód
     */
    public void inject(Virologist v, GeneticCode code){
        Virologist currentPlayer = game.getCurrentPlayer();
        actionMessage =  "Trying to inject " + v.getName() + " with " + code.getName() + "...";
        currentPlayer.inject(v, code);

    }

    /**
     * Virológus körének vége
     */
    public void endTurn(){
        Virologist currentPlayer = game.getCurrentPlayer();
        currentPlayer.endTurn();
        currentPlayer.detach(window);
        currentPlayer = game.getCurrentPlayer();
        currentPlayer.attach(window);
        actionMessage = "My turn...";
        notifyAllObservers();
    }

    /**
     * Állapot visszacsatolás
     * @return Cselekvés üzenete
     */
    public String getActionMessage(){
        return actionMessage;
    }
}

