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
	public Agent create(Virologist v) throws GeneticCodeException
	{
		try {
			v.removeNucleotide(nucleotidePrice);
		} catch (Exception e) {
			throw new GeneticCodeException("Failed to remove nucleotide.");
		}

		try{
			v.removeAminoAcid(aminoAcidPrice);
		}
		catch(Exception e){
			v.addNucleotide(nucleotidePrice);
			throw new GeneticCodeException("Failed to remove amino acid.");
		}
		return new Block(turnsLeft*playerCount);
	}

}
