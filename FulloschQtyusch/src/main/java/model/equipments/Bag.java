package model.equipments;


import model.Virologist;

/**
 * Olyan felszerelés, amely növeli a maximális nukleotid és aminosav tárhelyet
 */
public class Bag extends Equipment
{
	/**
	 * Növelendő mennyiség
	 */
	private final int delta;

	/**
	 * Zsák felszerelés létrehozása
	 * Beállítja a növelő mennyiséget
	 */
	public Bag(){
		delta = 5;
	}

	/**
	 * Visszaadja a növelő mennyiséget
	 */
	public int getDelta() {
		return delta;
	}

	/**
	 * Növelia a maximális nukleotid és aminosav tárhelyet
	 * @param v viselő virológus
	 */
	@Override
	public void apply(Virologist v) {
		v.increaseLimit(delta);
	}

	/**
	 * Megszünteti a növelő hatását
	 * @param v viselő virológus
	 */
	@Override
	public void disable(Virologist v) {
		v.decreaseLimit(delta);
	}

}
