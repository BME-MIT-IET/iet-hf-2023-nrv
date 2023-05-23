import java.io.IOException;

/**
 * A p�lyale�r� nyelvben egy mez�t jelk�pez� oszt�ly �s param�terei
 */
public class Field {
    /**
     * Azonos t�pus� mez� sz�ml�l�ja az automatikus elnevez�shez
     */
    private static int counter = 1;
    /**
     * Mez� t�pusa
     */
    protected String type;
    /**
     * Mez� neve
     */
    private String name;
    /**
     * Mez�n tal�lhat� felszerel�s
     */
    protected String equipment;
    /**
     * Mez� param�tere (pl. genetikai k�d, vagy felszerel�s)
     */
    protected String param;

    /**
     * Mez� konstruktora
     */
    public Field(){
        setName("F"+ counter);
        counter++;

        type = "Field";


        int choice = Main.RND.nextInt(Main.EQUIPMENTS.length+1);
        if(choice == 0)
            equipment = null;
        else
            equipment = Main.EQUIPMENTS[choice-1];

        param = null;
    }

    /**
     * Mez�t ki�r� f�ggv�ny
     * @throws IOException ha nem siker�lt a file-ba �r�s
     */
    public void print() throws IOException {

        Main.FW.append("Field\nType ").append(type).append("\n");
        if(param != null)
            Main.FW.append("Param ").append(param).append("\n");
        Main.FW.append("Name ").append(getName()).append("\n");
        if(equipment != null)
            Main.FW.append("Equipment ").append(equipment).append("\n");
        Main.FW.append("end\n");
    }

    /**
     * @return mez� neve
     */
    public String getName() {
        return name;
    }

    /**
     * Mez� nev�nek be�ll�t�sa
     * @param name a be�ll�tand� n�v
     */
    public void setName(String name) {
        this.name = name;
    }
}
