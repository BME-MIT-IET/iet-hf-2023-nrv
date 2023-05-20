A következő funkciókat szeretnénk tesztelni manuálisan:

- Játékos-mező interakció:
    - A virológus el tud lépni szomszédos mezőbe.
            
          A virológus sikeresen lépett egy szomszédos mezőre. Az Actions->move-val.
    - Letapogatás egy laborban, aminek a hatására a virológus megtanul egy még nem ismert genetikai kódot.
  
          Laborban az Actions->learn-nel megtanulja az adott genetikai kódot.
    - Felszerelés gyűjtése egy óvóhelyen, üres óvóhelyen nem szerez már semmit
    - Már van három felszerelése, ezért nem tud újat felvenni.
    - Anyaggyűjtés raktárban, +5 egységnyit kapunk.
          
          Anyaggyűjtés működik, nem üres raktárba lépve, majd Actions->collect-tel fel tudtam venni az anyagot.
    - Szabad területtel nincs interakció.
  
          Szabad területen hiába Actions->collectelünk vagy Actions->learnölünk, nem történik semmi.
          Erről azonban semmilyen visszajelzést nem kapunk.
    - Max 3 cselekvés egy körben
  
          Ha 0/3-at mutat a UI, nem hajtódik végre semmi. Játék indulásakor rossz akciószám van, nem 3-ról indulunk.
          Ezt fixáltam, a  map fájlokban.
    - Fertőző laborban megfertőződik medvevírussal a virológus
          
          A medvevírus hatására a virológus megfertőződik, de a UI-ban nem tapasztaljuk a fertőzést.
          Csak onnan tudjuk, hogy lépésnél nem oda lép, ahova monjuk, hanem random megy.

- Játékos-játékos interakció:
    - Vakcina / Vírus használatkor az ágens költsége levonódik.
    - Vítustánc ágens hatására véletlenül kezd el lépkedni a virológus, 1 kör erejéig.
    - Blokkoló ágens hatására védve van a virológus ágensektől, 2 kör erejéig.
    - Blokkoló ágens hatására elveszti a saját magára kent ágensek hatását.
    - Bénító ágens alatt álló virológus egy kör erejéig, nem tud interakcióba lépni semmivel.
    - A játékos kifoszt egy bénított virológust.
    - Felejtő ágens hatására a virológus elveszti a megtanult genetikai kódokat.
    - Medve megfertőzi a védtelen virológust

- Játékos-felszerelés interakció
    - Védőköpeny az esetek többségében véd az ágensfelkenéstől
    - Zsák növeli 5-tel a maximális nukleotid és aminosav mennyiséget, de a az aktuális mennyiséget nem
    - Kesztyű visszadobja az ágenst
    - Fejszével megölik a medvét
