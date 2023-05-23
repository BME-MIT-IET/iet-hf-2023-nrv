/**
 * A p�lyale�r�nyelvben laborat�riumot jelk�pez� oszt�ly
 */
public class Laboratory extends Field{
    /**
     * Azonos t�pus� mez� sz�ml�l�ja az automatikus elnevez�shez
     */
    private static int counter = 1;
    /**
     * Konstruktor
     */
    public Laboratory(){
        setName("L"+ counter);
        counter++;

        type = "Laboratory";

        equipment = null;

        int choice = Main.RND.nextInt(Main.CODES.length);
        param = Main.CODES[choice];
    }
}
