package model.strategy;

import model.Virologist;
import model.map.Field;
import model.map.Material;

/**
 * Anyag gyüjtésért felelős stratégiát reprezentáló interfész.
 */
public interface ICollectStr {

	/**
	 * Anyag gyüjtésekor meghívott, az interakciót reprezentáló függvény.
	 * @param v Gyüjtő virológus
	 * @param f A mező, ahol van az anyag
	 */
	void collect(Virologist v, Field f, Material materialType);

}
