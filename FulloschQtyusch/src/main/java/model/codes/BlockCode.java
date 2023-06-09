package model.codes;


import model.Virologist;
import model.agents.Agent;
import model.agents.Block;

import static model.Game.playerCount;


/**
 * Blokkoló hatású ágenshez tartozó genetikai kód
 */
public class BlockCode extends GeneticCode
{
	/**
	 * Konstruktor, mely beállítja a kreálható ágens költségeit és
	 * hatásánakl időtartamát
	 */
	public BlockCode() {
		aminoAcidPrice = 4;
		nucleotidePrice = 3;
		turnsLeft = 2;
	}

	/**
	 * Létrehoz egy Block Agent-t (ágens), és visszatér vele.
	 * Ha nem hozható létre az Agent, mert nincs hozzá elég anyag a paraméterül kapott virológusnak,
	 * akkor kivételt dob.
	 * @param v a virológus, aki szeretne ágenst készíteni
	 * @return az elkészített ágens
	 * @throws GeneticCodeException ha a virológusnak nem volt elég anyaga az ágenskészítéshez
	 */
	@Override
	public Agent create(Virologist v) throws GeneticCodeException
	{
		removePrice(v);
		return new Block(turnsLeft*playerCount);
	}

}
