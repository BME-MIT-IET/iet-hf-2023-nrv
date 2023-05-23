
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A program vez�rl� oszt�lya
 */
public class Main {
    /**
     * A p�lyale�r�nyelv �ltal elfogadott felszerel�sek nevei
     */
    public static final String[] EQUIPMENTS = {"Axe", "Bag", "Cloak", "Glove"};
    /**
     * A p�lyale�r�nyelv �ltal elfogadott genetikai k�dok nevei
     */
    public static final String[] CODES = {"BlockCode", "StunCode", "ChoreaCode", "ForgetCode"};
    /**
     * A l�trehozand� p�lya m�ret�nek ar�nyoss�gi t�nyez�je
     */
    private static int size = 5;
    /**
     * A l�trehozand� virol�gusok nevei - opcion�lis
     */
    private static ArrayList<String> names;
    /**
     * A l�trehozott mez�k
     */
    public static final List<Field> FIELDS;
    /**
     * A p�ly�t file-ba ki�r� eszk�z
     */
    public static final FileWriter FW;
    /**
     * K�zponti random gener�tor
     */
    public static final Random RND;

    /**
     * A program bel�p�si pontja
     * @param args [j�t�kosok sz�ma: eg�sz sz�m, k�telez�], [a l�trehozand� file el�r�si �tja: ""-kel �vezett sz�veg, opcion�lis],
     *             [a p�lya m�rete: eg�sz sz�m, ennek a j�t�kosok sz�m�val vett szorzat�val fog megegyezni a mez�k sz�ma, opcion�lis
     *              - de felt�tele a kor�bbi �sszes opcion�lis param�ter megad�sa k�telez� jelleggel -], [a l�trehozand� virol�gusok nevei:
     *              egym�s utan ""-kel �vezve tetsz�leges sok sz�m� virol�gusn�v megadhat�, opcion�lis
     *              - de felt�tele a kor�bbi �sszes opcion�lis param�ter megad�sa k�telez� jellgel -]
     * @throws IOException ha nem siker�lt a file-ba �r�s
     */
    public static void main(String[] args) throws IOException {
        if(args != null) {

            names = new ArrayList<>();
            int playerCount = Integer.parseInt(args[0]);
            String path = "map.txt";

            if(args.length > 1){

                path = args[1];

                if(args.length > 2){

                    size = Integer.parseInt(args[2]);

                    if(args.length > 3){

                        names.addAll(Arrays.asList(args).subList(3, args.length));
                    }
                }
            }

            FW = new FileWriter(path);
            RND = new Random();

            createFields(playerCount);
            createPlayers(playerCount);

            FW.close();
        }
    }

    /**
     * Mez�k gener�l�sa
     * @param playerCount j�t�kosok sz�ma
     * @throws IOException ha nem siker�lt a file-ba �r�s
     */
    private static void createFields(int playerCount) throws IOException {
        fields = new ArrayList<>();

        for(int i = 0; i< playerCount*size;i++){
            int j = RND.nextInt(5);
            switch(j){
                case 0:
                    fields.add(new Field());
                    break;
                case 1:
                    fields.add(new Shelter());
                    break;
                case 2:
                    fields.add(new Warehouse());
                    break;
                case 3:
                    fields.add(new Laboratory());
                    break;
                case 4:
                    fields.add(new InfectedLaboratory());
                    break;
            }
        }

        for(Field field: fields){
            field.print();
        }

        FW.append("Neighbours\n");

        //build tree of fields
        for(int i = 0; i < fields.size();i++){
            for(int j = 0; j < i; j++){
                boolean b = RND.nextBoolean();
                if(b){
                    FW.append(fields.get(i).getName()).append(" ").append(fields.get(j).getName()).append("\n");
                    break;
                }
                if(j == i-1){
                    FW.append(fields.get(i).getName()).append(" ").append(fields.get(j).getName()).append("\n");
                }
            }
        }

        //create web of fields
        //double rows no problem, the model input ignores it
        for(int i = 0; i < fields.size();i++){
            for(int j = 0; j < fields.size(); j++){
                if(i != j) {
                    int b = RND.nextInt(100);
                    if (b%4 == 0) {
                        FW.append(fields.get(i).getName()).append(" ").append(fields.get(j).getName()).append("\n");
                    }
                }
            }
        }

        FW.append("end\n");
    }

    /**
     * Virol�gusok gener�l�sa
     * @param playerCount j�t�kosok sz�ma
     * @throws IOException ha nem siker�lt a file-ba �r�s
     */
    private static void createPlayers(int playerCount) throws IOException {
        for(int i = 0; i < playerCount; i++){
            Virologist v;
            if(names.size() > i)
                v = new Virologist(names.get(i));
            else
                v = new Virologist();

            v.print();
        }
    }
}
