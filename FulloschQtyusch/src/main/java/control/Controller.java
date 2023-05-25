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
     * Van-e még akciója a játékosnak
     * @param v a virológus, akinek a visszalévő akcióinak számát szeretnénk lekérdezni
     * @return a virológus visszalévő akcióinak száma egyenlő-e 0-val
     */
    private boolean hasNoActions(Virologist v){
        if(v.getActionCount() == 0){
            actionMessage = "I have no action left!";
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
        Virologist currentPlayer = game.getCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;
        int before = game.getVirologists().size();
        currentPlayer.attack(v);
        int after = game.getVirologists().size();
        if(after < before){
            actionMessage = "I killed " + v.getName() + "!";
        }
        else{
            actionMessage = "I couldn't kill " + v.getName() + "!";
        }
        if(!currentPlayer.equals(game.getCurrentPlayer())){
            currentPlayer.detach(window);
            currentPlayer = game.getCurrentPlayer();
            currentPlayer.attach(window);
            actionMessage = "My turn...";
        }
        notifyAllObservers();
    }

    /**
     * Virológus mozgatása
     * @param f Cél mező
     */
    public void move(Field f){
        Virologist currentPlayer = game.getCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;
        Field before  = currentPlayer.getField();
        currentPlayer.move(f);
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
        Virologist currentPlayer = game.getCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int before = currentPlayer.getEquipments().size();
        currentPlayer.drop();
        int after = currentPlayer.getEquipments().size();
        if(before > after){
            actionMessage = "I dropped an equipment!";
        }
        else{
            actionMessage = "I have no equipment to drop!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus aminosav lopása
     * @param v Célpont virológus
     */
    public void lootAminoFrom(Virologist v){
        Virologist currentPlayer = game.getCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;
        int before = currentPlayer.getAminoAcid();
        currentPlayer.lootAminoAcidFrom(v);
        int after = currentPlayer.getAminoAcid();
        if(before < after){
            actionMessage = "I looted some amino acid from "+ v.getName()+"!";
        }
        else {
            actionMessage = "I couldn't loot amino acid!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus nukleotid lopása
     * @param v Célpont virológus
     */
    public void lootNucleoFrom(Virologist v){
        Virologist currentPlayer = game.getCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int before = currentPlayer.getNucleotide();
        currentPlayer.lootNucleotideFrom(v);
        int after = currentPlayer.getNucleotide();
        if(before < after){
            actionMessage = "I looted some nucleotide" + v.getName() + "!";
        }
        else{
            actionMessage = "I couldn't loot nucleotide";
        }
        notifyAllObservers();
    }

    /**
     * Virológus felszerelés lopása
     * @param v Célpont virológus
     */
    public void lootEquipmentFrom(Virologist v){
        Virologist currentPlayer = game.getCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int before = currentPlayer.getEquipments().size();
        currentPlayer.lootEquipmentFrom(v);
        int after = currentPlayer.getEquipments().size();
        if(before < after){
            actionMessage = "I looted some equipment from " + v.getName() + "!";
        }
        else{
            actionMessage = "I couldn't loot equipment from " + v.getName() + "!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus anyag gyűjtése
     */
    public void collect(Material materialtype){
        Virologist currentPlayer = game.getCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int beforeAmino = currentPlayer.getAminoAcid();
        int beforeNucleo = currentPlayer.getNucleotide();
        currentPlayer.collect(materialtype);
        int afterAmino = currentPlayer.getAminoAcid();
        int afterNucleo = currentPlayer.getNucleotide();
        if(beforeAmino != afterAmino && beforeNucleo != afterNucleo){
            actionMessage = "I collected some amino and nucleotide!";
        }
        else if(beforeAmino != afterAmino){
            actionMessage = "I collected some amino!";
        }
        else if(beforeNucleo != afterNucleo){
            actionMessage = "I collected some nucleotide!";
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
        Virologist currentPlayer = game.getCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int before = currentPlayer.getGeneticCodes().size();
        currentPlayer.learn();
        int after = currentPlayer.getGeneticCodes().size();
        if(before < after){
            actionMessage = "I learned "+ currentPlayer.getGeneticCodes().get(after-1).getName() + " genetic code!";
        }
        else{
            actionMessage = "I already know this genetic code!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus felszerelés felvétele
     */
    public void equip(){
        Virologist currentPlayer = game.getCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        int before = currentPlayer.getEquipments().size();
        currentPlayer.equip();
        int after = currentPlayer.getEquipments().size();
        if(before < after){
            actionMessage = "I equipped a new item!";
        }
        else{
            actionMessage = "I couldn't equip anything!";
        }
        notifyAllObservers();
    }

    /**
     * Virológus injektálása
     * @param v Célpont virológus
     * @param code Injektáláshoz szükséges genetikai kód
     */
    public void inject(Virologist v, GeneticCode code){
        Virologist currentPlayer = game.getCurrentPlayer();
        if (hasNoActions(currentPlayer)) return;

        actionMessage =  "Trying to inject " + v.getName() + " with " + code.getName() + "...";
        currentPlayer.inject(v, code);
        notifyAllObservers();
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

