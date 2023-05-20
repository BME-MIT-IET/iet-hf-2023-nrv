package control;

import model.Game;

import model.Virologist;
import model.codes.GeneticCode;
import model.map.*;
import model.Subject;
import view.Window;
//TODO comment
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
        Virologist first = game.GetCurrentPlayer();
        first.attach(window);
    }

    /**
     * Virológus megtámadása
     * @param v Megtámadott virológus
     */
    public void attack(Virologist v){
        Virologist currentPlayer = game.GetCurrentPlayer();
        actionMessage = v.getName() + " might be dead by now▄";
        currentPlayer.Attack(v);
        if(!currentPlayer.equals(game.GetCurrentPlayer())){
            currentPlayer.detach(window);
            currentPlayer = game.GetCurrentPlayer();
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
        Virologist currentPlayer = game.GetCurrentPlayer();
        actionMessage =  "Trying to move to " + f.getName() + "...";
        currentPlayer.Move(f);

    }

    /**
     * Virológus felszerelésének eldobása
     */
    public void drop(){
        Virologist currentPlayer = game.GetCurrentPlayer();
        actionMessage = "Trying to drop an equipmnet...";
        currentPlayer.Drop();


    }

    /**
     * Virológus aminosav lopása
     * @param v Célpont virológus
     */
    public void lootAminoFrom(Virologist v){
        Virologist currentPlayer = game.GetCurrentPlayer();
        actionMessage = "Trying to loot amino acid form " + v.getName() + "...";
        currentPlayer.LootAminoAcidFrom(v);


    }

    /**
     * Virológus nukleotid lopása
     * @param v Célpont virológus
     */
    public void lootNucleoFrom(Virologist v){
        Virologist currentPlayer = game.GetCurrentPlayer();
        actionMessage = "Trying to nucleotide acid form " + v.getName() + "...";
        currentPlayer.LootNucleotideFrom(v);


    }

    /**
     * Virológus felszerelés lopása
     * @param v Célpont virológus
     */
    public void lootEquipmentFrom(Virologist v){
        Virologist currentPlayer = game.GetCurrentPlayer();
        actionMessage = "Trying to equipment acid form " + v.getName() + "...";
        currentPlayer.LootEquipmentFrom(v);


    }

    /**
     * Virológus anyag gyűjtése
     */
    public void collect(){
        Virologist currentPlayer = game.GetCurrentPlayer();
        actionMessage = "Trying to collect material...";
        currentPlayer.Collect();

    }

    /**
     * Virológus genetikai kód tanulása
     */
    public void learn(){
        Virologist currentPlayer = game.GetCurrentPlayer();
        int before = currentPlayer.getGeneticCodes().size();
        currentPlayer.Learn();
        int after = currentPlayer.getGeneticCodes().size();
        if(before < after){
            actionMessage = "You learned a new genetic code!";
        }
        else{
            actionMessage = "You already know this genetic code!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus felszerelés felvétele
     */
    public void equip(){
        Virologist currentPlayer = game.GetCurrentPlayer();
        int before = currentPlayer.GetEquipments().size();
        currentPlayer.Equip();
        int after = currentPlayer.GetEquipments().size();
        if(before < after){
            actionMessage = "You equipped a new item!";
        }
        else{
            actionMessage = "You couldn't equip anything!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus injektálása
     * @param v Célpont virológus
     * @param code Injektáláshoz szükséges genetikai kód
     */
    public void inject(Virologist v, GeneticCode code){
        Virologist currentPlayer = game.GetCurrentPlayer();
        actionMessage =  "Trying to inject " + v.getName() + " with " + code.getName() + "...";
        currentPlayer.Inject(v, code);

    }

    /**
     * Virológus körének vége
     */
    public void endTurn(){
        Virologist currentPlayer = game.GetCurrentPlayer();
        currentPlayer.EndTurn();
        currentPlayer.detach(window);
        currentPlayer = game.GetCurrentPlayer();
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

