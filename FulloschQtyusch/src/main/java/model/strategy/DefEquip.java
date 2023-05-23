package model.strategy;



import model.Virologist;
import model.map.Field;

/**
 * Default engedélyezett a tárgyfelvétel (védőfelszerlés), így ezt a stratégiát valósítja meg.
 */
public class DefEquip implements IEquipStr
{
	/**
	 * A parqaméterül kapott virológussal megpróbáltat felvenni egy védőfelszerelést, valamint csökkenti körei számát.
	 * @param v A felvételt végző virológus.
	 * @param f Erről a mezőről próbálkozik védőfelszerelés felvételével v.
	 */
	@Override
	public void equip(Virologist v, Field f)
	{
		f.pickUpEquipment(v);
		v.decreaseActions();
	}
}
