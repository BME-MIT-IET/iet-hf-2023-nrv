## Célkitűzés

- A játékban elérhető use-case-ek tesztelése
- Detektált hibák javítása
- Funkciók helyes működésének igazolása

A feature fájlok tartalma a tesztek dokumentációjának tekinthető. Ezért itt csak az eredmények, és az elvégzett javítások lesznek feltüntetve.

# Teszt: Virológos mozgása

Minden teszt helyesen futott.
Megjegyzés: A random mozgás nem tesztelhető.

# Teszt: Virológus anyagot gyűjthet

WareHouse mezőn lehet anyagot gyűjteni, a dokumentáció szerint:

- "Anyag gyűjtése
  Nem determinisztikus esetben a paraméterül kapott virológus anyagkészletét deltával növeli meg, random választva a 2 fajta anyag közül
  Determinisztikus esetben a paraméterül kapott virológus anyagkészletét deltával növeli meg, a kiválasztott anyag közül"

- A metódus csak nem determinisztikus működést biztosít.
