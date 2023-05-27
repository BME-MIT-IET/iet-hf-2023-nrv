## Megvalósítani tervezett tesztek:

### Model package

- Virologist osztály tesztelése

  - Az összes tesztelhető függvénytt leteszteljük.

- #### _Agents package_

- Bear osztály tesztelése

  - applyStrategy

- Block osztály tesztelése
  - applyStrategy
  - apply
- Chorea osztály tesztelése

  - apply

- Forget osztály tesztelése
  - apply
- Stun osztály tesztelése

  - applyStrategy

- #### _Codes package_
- BlockCode osztály tesztelése

  - create

- ChoreaCode osztály tesztelése
  - create
- ForgetCode osztály tesztelése

  - create

- StunCode osztály tesztelése

  - create

- #### _Equipments package_
- Axe osztály tesztelése

  - attack
  - applyStrategy

- Bag osztály tesztelése

  - apply
  - disable

- Cloak osztály tesztelése

  - applyStrategy-t a randomsága miatt nem igazán lehet biztosra tesztelni

- Gloves osztály tesztelése

  - applyStrategy
  - injected
  - injectedBy: másik virológus által injected

- #### _Map package_
- Field osztály tesztelése
  -hiba: a szomszédos mezők beállításánál csak egyirányú kapcsolat jön létre,
  ez probléma, mivel a hívónak kell tudnia, hogy a másik mezőn is be kell állítania az asszociációt.
  -javítás: a setField metódus állítsa be a paraméterben kapott field-en is a szomszéd kapcsolatot.
  -Laboratory osztály tesztelése
  -InfectedLaboratory osztály tesztelése
  -Warehouse osztály tesztelése

-megjegyzés: néhány metódust nem lehetett unit teszttel ellenőrizni, mivel azok privát váltózó állapotát változtatják

### Controller package

- Controller osztály tesztelése
  - attack
  - drop
  - inject: itt nem egyértelmű, hogy sikeres-e
  - javítás: az inject exceptiont magasabb szinten kezelni: controller kapja el
