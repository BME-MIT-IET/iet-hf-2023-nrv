### A projekt leírása
- A projekt egy körökre osztott játék, ahol a virológusok (játékosok) megpróbálják összegyűjteni az összes fellelhető genetikai kódot.
- A játék alapvetően virológusok, különböző fajta mezők, és a közöttük lezajló
  interakciókból áll.
- A virológusok a megfelelő mezőkre navigálva interakcióba léphetnek
  azokkal, ekkor főként tárgyakra tesznek szert.
- Ezen kívül lehet még genetikai kódokat nyerni az imént említett mechanizmusból.
- A virológusok egymás ellen is használhatják a különböző genetikai kódokból előállított ágenseket.

### A projekt felépítése
A projekt két fő részből áll:
- Pályákat generáló modul (MapGenerator)
- Játékot futtató modul (FulloschQtyusch), ami használja a pályákat
  - Ez az, amit a tesztelés során tesztelünk
  - A program az MVC tervezési mintát követi
    - A modell(model package) a játék logikáját tartalmazza
      - Ágensek
      - Genetikai kódok
      - Felszerelések
      - Térkép
      - Cselekvések
    - A nézet (view package) a játék megjelenítéséért felelős
      - Ehhez Java Swinget használ
      - Textures packagen belül ehhez tartoznak a képek
    - A kontroller (control package) pedig a kettő közötti kommunikációt végzi

### A cél:
- A cél a projekt átláthatóbbá tétele, a kód minőségének növelése statikus analízissel.
- A BDD tesztekkel a tesztelés és az üzleti követelmények közötti átjárhatóság és következetesség biztosítása.
- Unit tesztek írásával elősegíteni a kód minőségének javítását, a még meg nem talált hibák megtalálását és a jövőbeli konzisztencia garantálását.
- Manuális tesztekkel leellenőrizni a felhasználói szemszögből a megfelelő működést.
