package model.strategy;


import model.Virologist;

/**
 * A default zsákmányolási stratégia, engedélyezi a zsákmányolás kezdeményezését.
 */
public class DefLoot implements ILootStr
{
	/**
	 * Amniosav zsákmányolást kezdeményez v a target virológus felé.
	 * @param v A zsákmányoló virológus.
	 * @param target A kizsákmányolandó virológus.
	 */
	@Override
	public void lootAmino(Virologist v, Virologist target)
	{
		v.decreaseActions();
		target.stealAminoAcid(v);
	}

	/**
	 * Nucleotid zsákmányolást kezdeményez v a target virológus felé.
	 * @param v A zsákmányoló virológus.
	 * @param target A kizsákmányolandó virológus.
	 */
	@Override
	public void lootNucleotide(Virologist v, Virologist target)
	{
		v.decreaseActions();
		target.stealNucleotid(v);
	}

	/**
	 * Védőfelszerelés zsákmányolást kezdeményez v a target virológus felé.
	 * @param v A zsákmányoló virológus.
	 * @param target A kizsákmányolandó virológus.
	 */
	@Override
	public void lootEquipment(Virologist v, Virologist target)
	{
		v.decreaseActions();
		target.stealEquipment(v);
	}
}
