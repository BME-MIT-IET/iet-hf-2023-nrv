package view;

/**
 * Megfigyelő osztály, lehet értesíteni a feliratkozott objektumok felől
 * ha változás történt
 */
public interface Observer{
    /**
     * A megfigyelőt értesítő függvény
     */
    void update();
}
