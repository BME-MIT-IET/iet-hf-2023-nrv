A következő funkciókat szeretnénk tesztelni manuálisan:

- Játékos-mező interakció:
    - A virológus el tud lépni szomszédos mezőbe.
            
          A virológus sikeresen lépett egy szomszédos mezőre. Az Actions->move-val.
    - Letapogatás egy laborban, aminek a hatására a virológus megtanul egy még nem ismert genetikai kódot. 
    - Felszerelés gyűjtése egy óvóhelyen, üres óvóhelyen nem szerez már semmit
    - Már van három felszerelése, ezért nem tud újat felvenni.
    - Anyaggyűjtés raktárban, +5 egységnyit kapunk.
          
          Anyaggyűjtés működik, nem üres raktárba lépve, majd Actions->collect-tel fel tudtam venni az anyagot.
    - Szabad területen nincs akció.
    - Max 3 cselekvés egy körben
  
          Ha 0/3-at mutat a UI, nem hajtódik végre semmi, néha a játék rossz akciószám van, nem 3-ról indulunk.
    - Fertőző laborban megfertőződik medvevírussal a virológus

- Játékos-játékos interakció:
   - Vakcina / Vírus használatkor az ágens költsége levonódik.
   
         Az actions => inject => {virológus név} => {kód} hatására a kiválasztott virológusnak levonódott az ágens költsége és végbement az akció.
   - Vítustánc ágens hatására véletlenül kezd el lépkedni a virológus, 1 kör erejéig.

         Az actions => inject => Joe => ChoreaCode után Joe 1 körig véletlenül lépkedett.  
  - Blokkoló ágens hatására védve van a virológus ágensektől, 2 kör erejéig.
  
         Az actions => inject => Joe => BlockCode után Joe 2 körig védve volt. 
  - Blokkoló ágens hatására elveszti a saját magára kent ágensek hatását.
          
         Az actions => inject => Joe => BlockCode után Joe elvesztette a saját magára kent ágensek hatását.
  - Bénító ágens alatt álló virológus egy kör erejéig, nem tud interakcióba lépni semmivel.

        Az actions => inject => Joe => StunCode után Joe egy körig nem tudott interakcióba lépni semmivel.
  - A játékos kifoszt egy bénított virológust.
    
        Az actions => inject => Joe => StunCode után Joe egy körig nem tudott interakcióba lépni semmivel.
        Ezután megpróbáltam kifosztani Joe-t az actions => lootEquipmentFrom => Joe akcióval, de nem sikerült.
  - Felejtő ágens hatására a virológus elveszti a megtanult genetikai kódokat.

         Az actions gomb => inject => Joe => ForgetCode hatására a virológus elvesztette a megtanult genetikai
         kódokat.
         A nukleotid és aminosav értékek korrekten le lettek vonva.
  - Medve megfertőzi a védtelen virológust
           
         Az actions gomb => move => F7 hatására megfertőzödött a virológus.
         A másik virológus a medvevírus hatása alatt állt, így ez helyesen működik.
- Játékos-felszerelés interakció:
   - Védőköpeny az esetek többségében véd az ágensfelkenéstől

         Az actions gomb => inject => Joe => ForgetCode hatásától sikeresen megvédte a virológust.
  - Zsák növeli 5-tel a maximális nukleotid és aminosav mennyiséget, de a az aktuális mennyiséget nem

         A zsák ténylegesen növeli 5-tel a maximális értékeket.
  - Kesztyű visszadobja az ágenst

         Az actions gomb => inject => V1 => ChoreaCode hatására a V1 kesztyűje miatt visszadobódott az ágens.

  - Fejszével megölik a medvét
