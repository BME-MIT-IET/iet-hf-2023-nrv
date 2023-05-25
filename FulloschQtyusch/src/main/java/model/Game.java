package model;

import model.codes.GeneticCode;
import model.map.Field;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Egy singleton osztály, ami a játék kezeléséért felelős.
 * A játékot indítja, és lezárja.
 * Számontartja a játékosokat, és köreiket.(átadja egyiktől a másiknak az irányítást)
 * Mivel a játkosok köreit kezeli, tehát gyakorlatilag az időegységeket,
 * így az ő feladata az ágensek hátralévő idejét csökkenti is.
 */
public class Game extends Subject
{
	/**
	 * A pálya mezői
	 */
	private List<Field> fields;
	/**
	 * A játékosok száma
	 */
	public static int playerCount = 0;
	/**
	 * A játékban szereplő genetikai kódok
	 */
	private List<GeneticCode> codes;

	/**
	 * @return a tárolt virológusok listája
	 */
	public List<Virologist> getVirologists() {
		return virologists;
	}

	/**
	 * Determinisztikusságot/randomitást jelölő bit, alapból random működést jelez.
	 * Paranccsal lehet kikapcsolni egy jelszó segítségével.
	 */
	public boolean randOn = true;

	/**
	 * A játékban szereplő virpológusok
	 */
	private ArrayList<Virologist> virologists;

	/**
	 * Az aktuális játékos indexe a játékosok tömbjében.
	 */
	private int currentPlayer = 0;

	/**
	 * A Game osztály singleton -sága miatt private konstruktor.
	 */
	private Game(){
		fields = new ArrayList<>();
		codes = new ArrayList<>();
		virologists = new ArrayList<>();
	}
	/**
	 * A játékállapotot megvalósító példány.
	 */
	private static Game instance = null;

	/**
	 * Új játék létrehozása.
	 * @return az új játékot megvalósító Game példány.
	 */
	public static Game create(){
		if (instance == null){
			instance = new Game();
		}
		return instance;
	}

	/**
	 * Elindít egy új játékot, inicializálja a tagváltozók listáit.
	 */
	public void newGame() {
		fields = new ArrayList<>();
		codes = new ArrayList<>();
		virologists = new ArrayList<>();
		currentPlayer = 0;
	}

	/**
	 * Átadja az irányítást a sorrendben a következő játékosnak, az aktuálisnak lezárja a körét.
	 * @param codes a megismert genetikai kódok száma
	 */
	public void nextPlayer(int codes){
		if(codes == this.codes.size())
			endGame();
		else {
			for (Virologist v: virologists) {
				v.update();
			}
			currentPlayer++;
			if (currentPlayer == virologists.size()) currentPlayer = 0;
		}
	}

	/**
	 * @return az aktív játékos objektuma.
	 */
	public Virologist getCurrentPlayer(){
		return virologists.get(currentPlayer);
	}

	/**
	 * Befejezi a játékot és kihírdeti a nyertest
	 */
	public void endGame()
	{
		JFrame parent = new JFrame();
		if(virologists.isEmpty()){
			JOptionPane.showMessageDialog(parent, "Every virologist died, the agents won this game.");
		}
		JOptionPane.showMessageDialog(parent, virologists.get(currentPlayer).getName() + " won the game!");
		System.exit(0);
	}

	/**
	 * Hozzáad egy virológust a játékhoz
	 * @param v a hozzáadandó virológus
	 */
	public void addVirologist(Virologist v)
	{
		virologists.add(v);
		v.setGame(this);
		playerCount++;
	}

	/**
	 * Hozzáad egy új genetikai kódot a játékhoz
	 * @param gc hozzáadandó genetikai kód
	 */
	public GeneticCode addGeneticCode(GeneticCode gc){
		if (!codes.contains(gc)){
			codes.add(gc);
			return gc;
		} else{
			return codes.get(codes.indexOf(gc));
		}
	}

	/**
	 * Hozzáad egy mezőt a játékhoz
	 * @param f hozzáadandó mező
	 */
	public void addField(Field f){
		fields.add(f);
	}

	/**
	 * @return a mezők listája.
	 */
	public List<Field> getFields(){return fields;}

	/**
	 * Egy virológus eltávolítása a játékból.
	 * @param virologist az eltávolítandó virológus.
	 */

	public void RemoveVirologist(Virologist virologist) {
		if(virologists.isEmpty()){
			EndGame();
		}
		int i = virologists.indexOf(virologist);
		if (i < currentPlayer)
			currentPlayer--;
		virologists.remove(virologist);
		playerCount--;
	}
}
