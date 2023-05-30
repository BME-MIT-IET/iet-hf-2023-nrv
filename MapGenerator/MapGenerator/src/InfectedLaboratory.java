/**
 * A p�lyale�r�nyelvben fert�z�tt laborat�riumot jelk�pez� oszt�ly
 */
public class InfectedLaboratory extends Field{
    /**
     * Azonos t�pus� mez� sz�ml�l�ja az automatikus elnevez�shez
     */
    private static int counter = 1;

    /**
     * Konstruktor
     */
    public InfectedLaboratory(){
        setName("IL"+ counter);
        counter++;

        type = "InfectedLaboratory";

        equipment = null;

        int choice = Main.RND.nextInt(Main.CODES.length);
        param = Main.CODES[choice];
    }
}
