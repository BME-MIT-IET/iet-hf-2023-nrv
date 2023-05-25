package model.map;


import model.Virologist;

import java.util.Random;

/**
 * Olyan mező, amelyen anyag gyüjthető
 */
public class Warehouse extends Field
{
	private static final Random random = new Random();

	/**
	 * Ennyivel növeli a virológusok anyagát
	 */
	private int delta = 5;

	/**
	 * Anyag gyüjtése
	 * Nem determinisztikus esetben a paraméterül kapott virológus anyagkészletét deltával növeli meg, random választva a 2 fajta anyag közül
	 * Determinisztikus esetben a paraméterül kapott virológus anyagkészletét deltával növeli meg, a kiválasztott anyag közül
	 * @param v gyüjtő virológus
	 */
	@Override
	public void collectMaterial(Virologist v, Material materialType) {
		int r = random.nextInt(2) ;
		if (r == 0 || materialType.equals(Material.AMINO_ACID)) {
			v.addAminoAcid(delta);
		}
		else {
			v.addNucleotide(delta);
		}
	}

	/**
	 * A mezőn az anyagok tönkretételét szimbolizálja, nem vehető fel anyag ezután a mezőről
	 */
	@Override
	public void destroyMaterial(){
		delta = 0;
	}
}
