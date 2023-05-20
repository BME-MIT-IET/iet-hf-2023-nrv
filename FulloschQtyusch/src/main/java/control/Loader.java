package control;

import model.Game;
import model.Virologist;
import model.agents.Agent;
import model.codes.GeneticCode;
import model.equipments.Equipment;
import model.map.Field;
import view.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Architekturálisan a controller része. A pálya fileból betöltéséért felelős osztály.
 * A file formátumát a dokumentáció bemeneti nyelv szekciója írja le részletesen.
 */
public class Loader {

    public static class LoaderException extends RuntimeException {

        public LoaderException(String message) {
            super(message);
        }

    }

    /**
     * Beolvasásért felelős objektum
     */
    Scanner sc;

    /**
     * Az aktuálisan játszott játék.
     */
    private final Game game;

    /**
     * A pálya felépítéséhez szükséges átmeneti tároló, ami a név-mező összerendeléseket tartalmazza.
     */
    private final HashMap<String, Field> fields;

    /**
     * A bemeneti parancsokat, és a megvalósító metódusok kapcsolatát tároló objektum.
     */
    private static final LinkedHashMap<String, Method> inputs = new LinkedHashMap<>();

    /**
     * Az osztály inicializáláskor kigyűjti a bemeneti nyelv parancsaihoz tartozó metódusokat,
     * valamint hibát ad, ha valami eltérés van ezen metódusok szintaktikájában.
     * A bemeneti file formátumában lévő hibákért nem jelez!
     * @throws LoaderException Hiba ha nem megfelelő a metódusok formátum.
     */
    public Loader() throws LoaderException {
        try {
            for (Method method : Loader.class.getDeclaredMethods()) {
                if (method.isAnnotationPresent(LoaderInput.class)) {
                    inputs.put(method.getAnnotation(LoaderInput.class).name(), method);
                }
            }
        } catch (SecurityException e) {
            throw new LoaderException("Error in Loader methods!" + e.getMessage());
        }

        fields = new HashMap<>();
        game = Game.create();
        game.newGame();
    }

    /**
     * Betölti a játékot, a bemeneti nyelv szintaktikáját feltételezve.
     * Az adatokat a "path" elérési útvonalon található file-ból veszi.
     * @param path A bemeneti nyelv parancsait tartalmazó file elérési útvonala.
     * @return Az inicializált játékot adja vissza.
     * @throws LoaderException Ha a parancsok futtatása során hiba keletkezik, vagy nem létező parancsot kap bemenetként
     * kivétel dobódik.
     */
    public Game loadGame(String path) throws LoaderException {
        try {
            File inputFile = new File(path);
            sc = new Scanner(inputFile);

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                Method m = inputs.get(input);
                if (m != null) {
                    m.invoke(this);
                } else {
                    if (!input.isBlank())
                        throw new LoaderException("Unknown command in input file!");
                }
            }
        } catch (FileNotFoundException | InvocationTargetException | IllegalAccessException e) {
            throw new LoaderException("Failed to load game.");
        } finally {
            if (sc != null)
                sc.close();
        }

        return game;
    }

    /**
     * Név alapján példányosít egy osztályt, a default ctor-jával, ellenkező esetben kivétel dobódik.
     * @param className Az példányosítandó osztály "hosszú" - teljes neve
     * @return A példányosított osztály
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     */
    private Object createObject(String className) throws ClassNotFoundException, SecurityException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Class<?> c = Class.forName(className);
        return c.getDeclaredConstructor().newInstance();
    }

    /**
     * A pályaleíró nyelv része. A Mezők leírásáért felel. (részletekért lásd dokumentáció)
     * @throws LoaderException Jelzi, ha hiba volt a beolvasás során a szintaxisban,
     * amit nem lehet megoldani felhasználói bevatkozás nélkül.
     */
    @LoaderInput(name="Field")
    private void commandField() throws LoaderException {
        try{
            if (game == null)
                throw new LoaderException("Game is null.");

            HashMap<String, String> options = new HashMap<>();
            String line;
            while (!(line = sc.nextLine()).equals("end")){
                String[] command = line.split(" ");
                options.put(command[0], command[1]);
            }

            Field f = null;
            String arg = options.get("Param");

            if (arg == null){ //default ctor
                f = (Field) createObject("view.Drawable" + options.get("Type"));
            } else{
                //csúnya, de ez van
                switch(options.get("Type")){
                    case "Laboratory":
                        GeneticCode c1 = (GeneticCode) createObject("model.codes." + arg);
                        f = new DrawableLaboratory(game.addGeneticCode(c1));
                        break;
                    case "InfectedLaboratory":
                        GeneticCode c2 = (GeneticCode) createObject("model.codes." + arg);
                        f = new DrawableInfectedLaboratory(game.addGeneticCode(c2));
                        break;
                    case "Shelter":
                        Equipment e = (Equipment) createObject("view.Drawable"+arg);
                        f = new DrawableShelter(e);
                        break;
                    default:
                        break;
                }
            }
            String eqType = options.get("Equipment");
            if (eqType != null) {
                f.drop((Equipment) createObject("view.Drawable" + eqType));
            }
            if (options.get("Name") == null) throw new LoaderException("Name is mandatory!");
            if (fields.get(options.get("Name")) != null) throw new LoaderException("Field with Name already exists!");
            f.setName(options.get("Name"));
            fields.put(options.get("Name"), f);
            game.addField(f);
        } catch (Exception e){
            throw new LoaderException("Error in Field command format!");
        }
    }

    /**
     * A pályaleíró nyelv része. A Mezők szomszédsági viszonyainak leírásáért felel. (részletekért lásd dokumentáció)
     * @throws LoaderException Jelzi, ha hiba volt a beolvasás során a szintaxisban,
     * amit nem lehet megoldani felhasználói bevatkozás nélkül.
     */
    @LoaderInput(name="Neighbours")
    private void commandNeighbours() throws LoaderException {
        try{
            if (game == null)
                throw new LoaderException("Game is null.");
            String line;
            while (!(line = sc.nextLine()).equals("end")){
                String[] command = line.split(" ");
                Field f0 = fields.get(command[0]);
                Field f1 = fields.get(command[1]);
                f0.addNeighbour(f1);
                f1.addNeighbour(f0);
            }
        }catch(Exception e){
            throw new LoaderException("Error in Neighbours command format!");
        }
    }

    /**
     * A pályaleíró nyelv része. A Virológusok leírásáért felel. (részletekért lásd dokumentáció)
     * @throws LoaderException Jelzi, ha hiba volt a beolvasás során a szintaxisban,
     * amit nem lehet megoldani felhasználói bevatkozás nélkül.
     */
    @LoaderInput(name="Virologist")
    private void commandVirologist() throws LoaderException {
        try{
            if (game == null)
                throw new LoaderException("Game is null.");
            String line;
            Virologist v = new Virologist();
            String startingPos = "";
            while (!(line = sc.nextLine()).equals("end")){
                String[] command = line.split(" ");
                switch(command[0]){
                    case "Name":
                        v.setName(command[1]);
                        break;
                    case "Equipment":
                        v.addEquipment((Equipment) createObject("view.Drawable" + command[1]));
                        break;
                    case "Amino":
                        v.addAminoAcid(Integer.parseInt(command[1]));
                        break;
                    case "Nucleo":
                        v.addNucleotide(Integer.parseInt(command[1]));
                        break;
                    case "Agent":
                        Class<?> c= Class.forName("model.agents." + command[1]);
                        Agent a = (Agent) c.getConstructor(Integer.TYPE).newInstance(Integer.parseInt(command[2]));
                        a.apply(v);
                        v.addAgent(a);
                        a.applyStrategy(v);
                        break;
                    case "StartingPos":
                        startingPos = command[1]; //in case there are command after this that throw error we don't want to mess up the existing setup
                        break;
                    case "GeneticCode":
                        v.addGeneticCode((GeneticCode) createObject("model.codes." + command[1]));
                        break;
                    case "ActionCount":
                        v.setActionCount(Integer.parseInt(command[1]));
                        break;
                    default:
                        throw new LoaderException("Unknown command.");
                }
            }
            fields.get(startingPos).addVirologist(v);
            game.addVirologist(v);
        } catch (Exception e){
            throw new LoaderException("Error in Virologist command format!");
        }
    }

}
