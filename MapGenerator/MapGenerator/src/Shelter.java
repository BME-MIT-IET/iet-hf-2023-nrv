/**
 * A p�lyale�r�nyelvben �v�helyet jelk�pez� oszt�ly
 */
public class Shelter extends  Field {
    /**
     * Azonos t�pus� mez� sz�ml�l�ja az automatikus elnevez�shez
     */
    private static int counter = 1;
    /**
     * Konstruktor
     */
    public Shelter(){
        setName("S"+ counter);
        counter++;

        type = "Shelter";

        int choice = Main.rnd.nextInt(Main.equipments.length);
        param = Main.equipments[choice];

        equipment = null;
    }
}
