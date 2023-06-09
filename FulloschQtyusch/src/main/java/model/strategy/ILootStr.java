package model.strategy;

import model.Virologist;

/**
 * A virológus Aminosav, Nucleotid, védőfelszerelés zsákmányolási stratégiját megtesítendő osztályok interface-e.
 */
public interface ILootStr
{
	/**
	 * Aminosav zsákmányolás metódusa.
	 * @param v A zsákmányoló virológus.
	 * @param target A kizsákmányolandó virológus.
	 */
	void lootAmino(Virologist v, Virologist target);

	/**
	 * Nucleotid zsákmányolás metódusa.
	 * @param v A zsákmányoló virológus.
	 * @param target A kizsákmányolandó virológus.
	 */
	void lootNucleotide(Virologist v, Virologist target);

	/**
	 * Védőfelszerelés zsákmányolás metódusa.
	 * @param v A zsákmányoló virológus.
	 * @param target A kizsákmányolandó virológus.
	 */
	void lootEquipment(Virologist v, Virologist target);
}
