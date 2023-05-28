package model.strategy;


import model.Virologist;
import model.equipments.Equipment;

/**
 *  Virológusra irányuló kifosztásért felelős stratégia, egedélyezi a kifosztást
 */
public class Looted implements ILootedStr
{
	/**
	 * Felszerelésre irányuló kifosztás
	 * @param v kifosztó virológus
	 * @param from kifosztandó virológus
	 * @param e kifosztandó felszerelés
	 */
	@Override
	public void lootedForEquipment(Virologist v, Virologist from, Equipment e)
	{
		e.disable(from);
		from.removeEquipment(e);
		v.addEquipment(e);
	}

	/**
	 * Aminosavra irányuló kifosztás
	 * @param v kifosztó virológus
	 * @param from kifosztandó virológus
	 */
	@Override
	public void lootedForAminoAcid(Virologist v, Virologist from)
	{
		try {
			from.removeAminoAcid(1);
			v.addAminoAcid(1);
		} catch (Exception e) {
			//Nem volt mit elvenni
		}
	}

	/**
	 * Nucleotidra irányuló kifosztás
	 * @param v kifosztó virológus
	 * @param from kifosztandó virológus
	 */
	@Override
	public void lootedForNucleotide(Virologist v, Virologist from)
	{
		try{
			from.removeNucleotide(1);
			v.addNucleotide(1);
		}catch (Exception e){
			//Nem volt mit elvenni
		}
	}
}
