package model.equipments;


import model.Virologist;

/**
 * Védőfelszerelés, amely hatással van a viselőjére vagy/és stratégiát biztosít számára
 */
public abstract class Equipment
{
	/**
	 * Megadja a felszerelés nevét
	 * @return felszerelés neve
	 */
	public String getName(){
		return this.getClass().getSimpleName();
	}

	/**
	 * Megszakítja a hatását a viselőjén
	 * @param v viselő virológus
	 */
	public void disable(Virologist v) {
	}

	/**
	 * Alkalmazza hatását viselőjén
	 * @param v viselő virológus
	 */
	public void apply(Virologist v) {
	}

	/**
	 * Alkalmazza stratégiáját viselőjén
	 * @param v viselő virológus
	 */
	public void applyStrategy(Virologist v) {
	}
}
