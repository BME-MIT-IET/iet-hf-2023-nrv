package main;

import control.Controller;
import control.Loader;

import java.util.Random;

public class Main {

    /**
     * Alkalmazás belépési pontja.
     * @param args parancsori argumentumok.
     */
    public static void main(String[] args){
        try {
            Loader loader = new Loader();
            Random random = new Random();
            int chosenMap = random.nextInt(3)+1;
            new Controller(loader.loadGame("maps\\map"+chosenMap+".txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
