package model.strategy;


import model.Virologist;

/**
 * Zsákmányolási stratégia, ami nem engedélyez zsákmányolást.
 */
public class NoLoot implements ILootStr
{
	/**
	 * Nem Engedélyez Aminosav zsákmányolást.
	 * @param v A zsákmányoló virológus.
	 * @param target A kizsákmányolandó virológus.
	 */
	@Override
	public void lootAmino(Virologist v, Virologist target)
	{
		v.decreaseActions();
	}

	/**
	 * Nem Engedélyez Nukleotid zsákmányolást.
	 * @param v A zsákmányoló virológus.
	 * @param target A kizsákmányolandó virológus.
	 */
	@Override
	public void lootNucleotide(Virologist v, Virologist target)
	{
		v.decreaseActions();
	}

	/**
	 * Nem Engedélyez Védőfelszerelés zsákmányolást.
	 * @param v A zsákmányoló virológus.
	 * @param target A kizsákmányolandó virológus.
	 */
	@Override
	public void lootEquipment(Virologist v, Virologist target)
	{
		v.decreaseActions();
	}
}
