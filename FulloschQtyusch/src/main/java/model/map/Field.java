package model.map;

import model.Virologist;
import model.equipments.Equipment;

import java.util.List;
import java.util.ArrayList;

/**
 * Egy sima mező, amely tárolja az esetleg rajta lévő virológusokat, felszereléseket
 */
public class Field
{
	/**
	 * Szomszédos mezők
	 */
	protected ArrayList<Field> neighbours;
	/**
	 * Mezőn tartózkodó virológusok
	 */
	protected ArrayList<Virologist> virologists;
	/**
	 * Mezőn található felszerelések
	 */
	protected ArrayList<Equipment> equipments;

	/**
	 * Mező neve
	 */
	protected String name;

	/**
	 * Beállítja a mező nevét
	 * @param name név
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Megadja a mező nevét
	 * @return név
	 */
	public String getName(){
		return name;
	}

	/**
	 * Létrehozza a tárolókat
	 */
	public Field(){
		neighbours = new ArrayList<>();
		virologists = new ArrayList<>();
		equipments = new ArrayList<>();
	}

	/**
	 * Megadja a körülötte lévő mezőket
	 * @return szomszéd mezők
	 */
	public List<Field> getNeighbours() {
		return neighbours;
	}

	/**
	 * Megadja a mezőn tartózkodó virológusokat
	 * @return virológusok
	 */
	public List<Virologist> getVirologists(){
		return virologists;
	}

	/**
	 * Egy új szomszéddal bővíti a mezőt
	 * @param f új szomszéd
	 */
	public void addNeighbour(Field f) {
		if(!neighbours.contains(f))
			neighbours.add(f);
	}

	/**
	 * A mezőn az anyagok tönkre tételét szimbolizálja,
	 * de nem csinál semmit alapból, hiszen csak a Warehouse-on található anyag
	 */
	public void destroyMaterial(){
	}

	/**
	 * Virológus elhelyezése a mezőn
	 * @param v elehelyezendő virológus
	 */
	public void addVirologist(Virologist v) {
		virologists.add(v);
		v.setField(this);
	}

	/**
	 * Eltávolítja a virológust a mezőről
	 * @param v eltávolítandó virológus
	 */
	public void removeVirologist(Virologist v) {
		virologists.remove(v);
	}

	/**
	 * Eldobja az adott felszerelést a mezőre
	 * @param e eldobandó felszerelés
	 */
	public void drop(Equipment e) {
		equipments.add(e);
	}

	/**
	 * Genetikai kód tanulásáért felelős
	 * @param v tanuló virológus
	 */
	public void learnGeneticCode(Virologist v) {
	}

	/**
	 * Anyag gyüjtésért felelős
	 * @param v gyüjtő virológus
	 */
	public void collectMaterial(Virologist v) {
	}

	/**
	 * Felszerelés felvétele adott virológussal
	 * @param v felszedő virológus
	 */
	public void pickUpEquipment(Virologist v) {
		if (!equipments.isEmpty()) {
			Equipment equipment = equipments.remove(equipments.size()-1);
			//equipment.Apply(v); berakva AddEquipment-be
			v.addEquipment(equipment);
			//equipment.ApplyStrategy(v); berakva AddEquipment-be
		}
	}
}
