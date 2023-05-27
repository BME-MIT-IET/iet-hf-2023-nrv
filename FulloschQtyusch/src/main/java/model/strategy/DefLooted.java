package model.strategy;


import model.Virologist;
import model.equipments.Equipment;

/**
 * Alapértelmezett virológusra irányuló kifosztásért felelős stratégia, blokkolja a kifosztást
 */
public class DefLooted implements ILootedStr
{
	/**
	 * Felszerelésre irányuló kifosztás
	 * @param v kifosztó virológus
	 * @param from kifosztandó virológus
	 * @param e kifosztandó felszerelés
	 */
	@Override
	public void lootedForEquipment(Virologist v, Virologist from, Equipment e) {
	}

	/**
	 * Aminosavra irányuló kifosztás
	 * @param v kifosztó virológus
	 * @param from kifosztandó virológus
	 */
	@Override
	public void lootedForAminoAcid(Virologist v, Virologist from) {
	}

	/**
	 * Nukleotidra irányuló kifosztás
	 * @param v kifosztó virológus
	 * @param from kifosztandó virológus
	 */
	@Override
	public void lootedForNukleotide(Virologist v, Virologist from) {
	}
}
