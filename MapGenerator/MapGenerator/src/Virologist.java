import java.io.IOException;
import java.util.Random;
/**
 * A p�lyale�r�nyelvben a virol�gust jelk�pez� oszt�ly
 */
public class Virologist {
    /**
     * Virol�gus neve
     */
    private final String name;
    /**
     * Virol�gus h�tral�v� akci�i
     */
    private final int actions;
    /**
     * Virol�gus felszerel�se
     */
    private final String equipment;
    /**
     * Virol�gus aminosav mennyis�ge
     */
    private final int amino;
    /**
     * Virol�gus nukleotid mennyis�ge
     */
    private final int nucleo;
    /**
     * Virol�gus genetikai k�dja
     */
    private final String code;
    /**
     * Virol�gus poz�ci�ja
     */
    private final String pos;
    /**
     * Sz�ml�l� az automatikus elnevez�shez
     */
    private static int counter = 1;

    /**
     * Default konstruktor automata n�vad�ssal
     */
    public Virologist(){


        name = "V"+counter;
        counter++;

        actions = Main.rnd.nextInt(3)+1;

        int choice = Main.rnd.nextInt(Main.equipments.length+1);
        if(choice == 0)
            equipment = null;
        else
            equipment = Main.equipments[choice-1];

        amino = Main.rnd.nextInt(20);
        nucleo = Main.rnd.nextInt(20);

        choice = Main.rnd.nextInt(Main.codes.length+1);
        if(choice == 0)
            code = null;
        else
            code = Main.codes[choice-1];

        pos = Main.fields.get(Main.rnd.nextInt(Main.fields.size())).getName();
    }

    /**
     * Konstruktor speci�lis n�vad�ssal
     * @param name v�lasztott virol�gusn�v
     */
    public Virologist(String name){
        Random rnd = new Random();

        this.name = name;

        actions = rnd.nextInt(3)+1;

        int choice = rnd.nextInt(Main.equipments.length+1);
        if(choice == 0)
            equipment = null;
        else
            equipment = Main.equipments[choice-1];

        amino = rnd.nextInt(20);
        nucleo = rnd.nextInt(20);

        choice = rnd.nextInt(Main.codes.length+1);
        if(choice == 0)
            code = null;
        else
            code = Main.codes[choice-1];

        pos = Main.fields.get(rnd.nextInt(Main.fields.size())).getName();
    }

    /**
     * Virol�gust ki�r� f�ggv�ny
     * @throws IOException ha nem siker�lt a file-ba �r�s
     */
    public void print() throws IOException {

        Main.fw.append("Virologist\nName ").append(name).append("\nActionCount ").append(String.valueOf(actions)).append("\n");
        if(equipment != null)
            Main.fw.append("Equipment ").append(equipment).append("\n");
        Main.fw.append("Amino ").append(String.valueOf(amino)).append("\nNucleo ").append(String.valueOf(nucleo)).append("\n");
        if(code != null)
            Main.fw.append("GeneticCode ").append(code).append("\n");
        Main.fw.append("StartingPos ").append(pos).append("\nend\n");
    }

}
