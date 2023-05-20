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
     * Van-e még akciója a játékosnak
     * @param v a virológus, akinek a visszalévő akcióinak számát szeretnénk lekérdezni
     * @return a virológus visszalévő akcióinak száma egyenlő-e 0-val
     */
    private boolean hasNoActions(Virologist v){
        if(v.getActionCount() == 0){
            actionMessage = "You have no action left!";
            notifyAllObservers();
            return true;
        }
        return false;
    }


    /**
     * Virológus megtámadása
     * @param v Megtámadott virológus
     */
    public void attack(Virologist v){
        Virologist currentPlayer = game.GetCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

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
        if (hasNoActions(currentPlayer)) return;
        Field before  = currentPlayer.getField();
        currentPlayer.Move(f);
        if(!before.equals(f)){
            actionMessage =  "Successfully moved to " + f.getName();
        }
        else {
            actionMessage = "Cannot move to " + f.getName();
        }
        notifyAllObservers();
    }

    /**
     * Virológus felszerelésének eldobása
     */
    public void drop(){
        Virologist currentPlayer = game.GetCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int before = currentPlayer.GetEquipments().size();
        currentPlayer.Drop();
        int after = currentPlayer.GetEquipments().size();
        if(before > after){
            actionMessage = "You dropped an equipment!";
        }
        else{
            actionMessage = "You have no equipment to drop!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus aminosav lopása
     * @param v Célpont virológus
     */
    public void lootAminoFrom(Virologist v){
        Virologist currentPlayer = game.GetCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;
        int before = currentPlayer.GetAminoAcid();
        currentPlayer.LootAminoAcidFrom(v);
        int after = currentPlayer.GetAminoAcid();
        if(before < after){
            actionMessage = "You looted some amino acid from "+ v.getName()+"!";
        }
        else {
            actionMessage = "You couldn't loot amino acid!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus nukleotid lopása
     * @param v Célpont virológus
     */
    public void lootNucleoFrom(Virologist v){
        Virologist currentPlayer = game.GetCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int before = currentPlayer.GetNucleotide();
        currentPlayer.LootNucleotideFrom(v);
        int after = currentPlayer.GetNucleotide();
        if(before < after){
            actionMessage = "You looted some nucleotide" + v.getName() + "!";
        }
        else{
            actionMessage = "You couldn't loot nucleotide";
        }
        notifyAllObservers();
    }

    /**
     * Virológus felszerelés lopása
     * @param v Célpont virológus
     */
    public void lootEquipmentFrom(Virologist v){
        Virologist currentPlayer = game.GetCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int before = currentPlayer.GetEquipments().size();
        currentPlayer.LootEquipmentFrom(v);
        int after = currentPlayer.GetEquipments().size();
        if(before < after){
            actionMessage = "You looted some equipment from " + v.getName() + "!";
        }
        else{
            actionMessage = "You couldn't loot equipment from " + v.getName() + "!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus anyag gyűjtése
     */
    public void collect(){
        Virologist currentPlayer = game.GetCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int beforeAmino = currentPlayer.GetAminoAcid();
        int beforeNucleo = currentPlayer.GetNucleotide();
        currentPlayer.Collect();
        int afterAmino = currentPlayer.GetAminoAcid();
        int afterNucleo = currentPlayer.GetNucleotide();
        if(beforeAmino != afterAmino && beforeNucleo != afterNucleo){
            actionMessage = "You collected some amino and nucleotide!";
        }
        else if(beforeAmino != afterAmino){
            actionMessage = "You collected some amino!";
        }
        else if(beforeNucleo != afterNucleo){
            actionMessage = "You collected some nucleotide!";
        }
        else{
            actionMessage = "There is no material to collect!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus genetikai kód tanulása
     */
    public void learn(){
        Virologist currentPlayer = game.GetCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int before = currentPlayer.getGeneticCodes().size();
        currentPlayer.Learn();
        int after = currentPlayer.getGeneticCodes().size();
        if(before < after){
            actionMessage = "You learned "+ currentPlayer.getGeneticCodes().get(after-1).getName() + " genetic code!";
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
        if (hasNoActions(currentPlayer)) return;

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
        if (hasNoActions(currentPlayer)) return;

        actionMessage =  "Trying to inject " + v.getName() + " with " + code.getName() + "...";
        currentPlayer.Inject(v, code);
        notifyAllObservers();
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

