package model.equipments;


import model.Virologist;
import model.agents.Agent;
import model.strategy.IInjectedStr;

/**
 * Védőfelszerelés, amely stratégiát biztosít viselőjén, a virológusra kent ágenseket visszadobja a támadó virológusra,
 * azaz a kapott ágensnek nem lesznek hatással a viselőn és megpróbálja megkenni a támadó virológust a kapott ágenssel
 * Háromszori alkalmazás után elkopik, nem használható többé és levetődik a viselőjéről
 */
public class Glove extends Equipment implements IInjectedStr
{
	/**
	 * Használtsági szint
	 */
	private int useCount = 3;

	/**
	 * Alkalmazza az ágensek felől érinthetetlen stratégiát
	 * @param v viselő virológus
	 */
	@Override
	public void applyStrategy(Virologist v) {
		v.setInjectedStr(this);
	}

	/**
	 * Injected stratégia
	 * Nem virológusról irányuló kenés
	 * Ha nem használódott még el a felszerelés, akkor blokkolja és koptatja a felszerelést
	 * Ha elhasználódott, akkor eltávolítja magát a viselőjéről és újra alkalmazza a kenést
	 * @param v A virológus, akire felkenték az ágenst
	 * @param a A felkent ágens
	 */
	@Override
	public void injected(Virologist v, Agent a) {
		if (useCount > 0){
			useCount--;
		} else{
			v.removeEquipment(this);
			v.reset();
			v.targetedWith(a);
		}
	}

	/**
	 * Injected stratégia
	 * Virológusról irányuló felkenés
	 * Ha nem használódott még el a felszerelés, koptatja azt és vissazdobja a felkenő virológusra az ágenst
	 * Ha elhasználódott, akkor eltávolítja magát a viselőjéről és újra alkalmazza a kenést
	 * @param by Felkenő virológus
	 * @param injected Viselő virológus
	 * @param a A felkent ágens
	 */
	@Override
	public void injected(Virologist by, Virologist injected, Agent a) {
		if (useCount > 0){
			useCount--;
			by.targetedWith(injected, a);
		} else{
			injected.removeEquipment(this);
			injected.reset();
			injected.targetedWith(by, a);
		}
	}
}
