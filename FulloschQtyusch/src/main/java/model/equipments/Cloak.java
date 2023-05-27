package model.equipments;


import model.Virologist;
import model.strategy.NoInjected;

import java.util.Random;

/**
 * Védőfelszerelés, amely stratégiát biztosít viselőjén bizonyos eséllyel, érinthetetlenné teszi, ágensek felől
 */
public class Cloak extends Equipment
{
	private Random rand;

	/**
	 * Konstruktor, beállítja a védőfelszerelés árát
	 */
	public Cloak(Random ran)
	{
		this.rand = ran;
	}

	/**
	 * Alkalmazza az ágensek felől érinthetetlen stratégiát bizonyos eséllyel
	 * @param v viselő virológus
	 */
	@Override
	public void applyStrategy(Virologist v)
	{
		double r = rand.nextDouble();
		if (r < 0.823)
			v.setInjectedStr(new NoInjected());
	}
}
