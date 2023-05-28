package model.codes;



import model.Virologist;
import model.agents.Agent;
import model.agents.Forget;

import static model.Game.playerCount;

/**
 * Olyan genetikai kód, ami egy felejtő (Forget) típusú ágenst tud előállítani.
 */
public class ForgetCode extends GeneticCode
{
	/**
	 * Konstruktor, mely beállítja a létrehozhatóü felejtő ágens költségeit és időtartamát.
	 */
	public ForgetCode(){
		turnsLeft = 1;
		aminoAcidPrice = 6;
		nucleotidePrice = 6;
	}

	/**
	 * Létrehoz egy feleljtő (Forget) ágenst. és visszatér vele.
	 * @param v a virológus, aki szeretné ágenst készíteni.
	 * @return a létrehozott ágens.
	 * @throws GeneticCodeException ha nem hozható létre az Agent, mert nincs hozzá elég anyag a paraméterül kapott virológusnak.
	 */
	@Override
	public Agent create(Virologist v) throws GeneticCodeException
	{
		removePrice(v);
		return new Forget(turnsLeft*playerCount);
	}

}
