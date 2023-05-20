A következő funkciókat szeretnénk tesztelni manuálisan:

- Játékos-mező interakció:
    - A virológus el tud lépni szomszédos mezőbe.
            
          A virológus sikeresen lépett egy szomszédos mezőre. Az Actions -> move-val.
    - Letapogatás egy laborban, aminek a hatására a virológus megtanul egy még nem ismert genetikai kódot.
  
          Laborban az Actions -> learn-nel megtanulja az adott genetikai kódot.
          Javítás: A Controller learn metódusán javítva egyértelműsítjük, hogy sikeres volt-e a tanulás.
    - Felszerelés gyűjtése egy óvóhelyen, üres óvóhelyen nem szerez már semmit
           
          Óvóhelyen az Actions -> equip-pel tudunk felszerelést gyűjteni.
          Üres óvóhelyről tényleg nem szedünk fel semmit.
          Javítás: A Controller equip metódusán javítva egyértelműsítjük, hogy sikeres volt-e a felszerelés gyűjtése. 
    - Már van három felszerelése, ezért nem tud újat felvenni.
             
          Ha már van három felszerelésünk, nem tudunk újat felvenni.
          Ezt a UI-ban annyiból látjuk, hogy nem kerül új elem a felszerelésekhez.
    - Anyaggyűjtés raktárban, +5 egységnyit kapunk.
          
          Anyaggyűjtés működik, nem üres raktárba lépve, majd Actions -> collect-tel fel tudtam venni az anyagot.
    - Szabad területtel nincs interakció.
  
          Szabad területen hiába Actions -> collectelünk vagy Actions -> learnölünk, nem történik semmi.
          Erről azonban semmilyen visszajelzést nem kapunk.
    - Max 3 cselekvés egy körben
  
          Ha 0/3-at mutat a UI, nem hajtódik végre semmi. Játék indulásakor rossz akciószám van, nem 3-ról indulunk.
          Javítás 1: Ezt fixáltam, a  map fájlokban.
          Javítás 2: A Controllerben javítottam, hogy a UI-on is lássuk, ha már nincs akciónk.
    - Fertőző laborban megfertőződik medvevírussal a virológus
          
          A medvevírus hatására a virológus megfertőződik, de a UI-ban nem tapasztaljuk a fertőzést.
          Csak onnan tudjuk, hogy lépésnél nem oda lép, ahova monjuk, hanem random megy.
          Ezt megoldottuk -> név mellé (Bear) felirat került, ha a medvevírus hatása alatt áll.

- Játékos-játékos interakció:
    - Vakcina / Vírus használatkor az ágens költsége levonódik.
            
          Az Actions -> inject -> {virológus név} -> {kód} hatására a kiválasztott virológusnak levonódott az ágens költsége és végbement az akció.
    - Vítustánc ágens hatására véletlenül kezd el lépkedni a virológus, 1 kör erejéig.

          Az Actions -> inject -> Joe -> ChoreaCode után Joe 1 körig véletlenül lépkedett.  
    - Blokkoló ágens hatására védve van a virológus ágensektől, 2 kör erejéig.
  
          Az Actions -> inject -> Joe -> BlockCode után Joe 2 körig védve volt. 
    - Blokkoló ágens hatására elveszti a saját magára kent ágensek hatását.
          
          Az Actions -> inject -> Joe -> BlockCode után Joe elvesztette a saját magára kent ágensek hatását.
    - Bénító ágens alatt álló virológus egy kör erejéig, nem tud interakcióba lépni semmivel.

          Az Actions -> inject -> Joe -> StunCode után Joe egy körig nem tudott interakcióba lépni semmivel.
    - A játékos kifoszt egy bénított virológust.
    
          Az Actions -> inject -> Joe -> StunCode után Joe egy körig nem tudott interakcióba lépni semmivel.
          Ezután megpróbáltam kifosztani Joe-t az Actions -> lootEquipmentFrom -> Joe akcióval, de nem sikerült.
    - Felejtő ágens hatására a virológus elveszti a megtanult genetikai kódokat.

          Az Actions gomb -> inject -> Joe -> ForgetCode hatására a virológus elvesztette a megtanult genetikai
          kódokat.
          A nukleotid és aminosav értékek korrekten le lettek vonva.
    - Medve megfertőzi a védtelen virológust
           
          Az Actions gomb -> move -> F7 hatására megfertőzödött a virológus.
          A másik virológus a medvevírus hatása alatt állt, így ez helyesen működik.
- Játékos-felszerelés interakció:
    - Védőköpeny az esetek többségében véd az ágensfelkenéstől

          Az Actions gomb -> inject -> Joe -> ForgetCode hatásától sikeresen megvédte a virológust.
    - Zsák növeli 5-tel a maximális nukleotid és aminosav mennyiséget, de a az aktuális mennyiséget nem

          A zsák ténylegesen növeli 5-tel a maximális értékeket.
    - Kesztyű visszadobja az ágenst

          Az Actions gomb -> inject -> V1 -> ChoreaCode hatására a V1 kesztyűje miatt visszadobódott az ágens.

    - Fejszével megölik a medvét

          A medvével egy mezőre érkező virulógus, akinek van fejszéje, sikeresen megölte a medvét.