package model.codes;


import model.Virologist;
import model.agents.Agent;

/**
 * Absztrakt ősként szolgál a különböző genetikai kódoknak.
 * Ezek felelősségei, hogy factory-ként szolgáljanak az ágenseknek,
 * hiszen ezek segítségével kell létrehozni őket,
 * ezen kívül tartalmazza az elkészítéshez tartozó aminosav, nukleotid tarifákat.
 */
public abstract class GeneticCode {
    public static class GeneticCodeException extends RuntimeException {
        public GeneticCodeException(String message) {
            super(message);
        }
    }

    protected void removePrice(Virologist v) throws GeneticCodeException {
        try {
            v.removeNucleotide(nucleotidePrice);
        } catch (Exception e) {
            throw new GeneticCodeException("Failed to remove nucleotide.");
        }
        try {
            v.removeAminoAcid(aminoAcidPrice);
        } catch (Exception e) {
            v.addNucleotide(nucleotidePrice);
            throw new GeneticCodeException("Failed to remove amino acid.");
        }
    }

    /**
     * Object equals függvényének felülírása 2 genetikai kód összehasonlítására.
     *
     * @param o az összehasonlítandó objektum.
     * @return megegyezik-e a két objektum.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        return this.getClass().isAssignableFrom(o.getClass());
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    /**
     * @return a genetikai kód típusa.
     */
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * @return a genetikai kód által készíthető ágens aminosav költsége.
     */
    public int getAminoAcidPrice() {
        return aminoAcidPrice;
    }

    /**
     * @return a genetikai kód által készíthető ágens nukleotid költsége.
     */
    public int getNucleotidePrice() {
        return nucleotidePrice;
    }

    /**
     * A kódhoz tartozó ágens előállításához szükséges aminosav mennyiség.
     */
    protected int aminoAcidPrice;

    /**
     * A kódhoz tartozó ágens előállításához szükséges nukleotid mennyiség.
     */
    protected int nucleotidePrice;

    /**
     * A kódhoz tartozó ágens hatásának ideje körökben mérve.
     */
    protected int turnsLeft;

    /**
     * Létrehoz egy, a kódhoz tartozó ágenst a megfelelő költség behajtásával a virológustól.
     *
     * @param v a virológus, aki szeretne ágenst készíteni
     * @return az elkészített ágens
     * @throws GeneticCodeException ha a virológusnak nem volt elég anyaga az ágenskészítéshez
     */
    public abstract Agent create(Virologist v) throws GeneticCodeException;
}
