package model;


import model.agents.*;
import model.codes.*;
import model.equipments.*;
import model.map.*;
import model.strategy.*;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *  A virologusert felelo osztaly. Ezeket az objektumokat
 *  fogjak tudni iranyitani a jatekosok a jatek soran es
 *  kulonbozo interakciokat vegrehajtani veluk.
 */
public class Virologist extends Subject
{
	public static class VirologistException extends RuntimeException {
		public VirologistException(String message) {
			super(message);
		}
	}

	private static final Random random = new Random();
	/**
	 * A virológus aktuális kontextusát adja vissza, azaz, hogy éppen milyen játékban vesz részt.
	 * @return
	 */
	public Game getContext(){return game;} //randomitás kikapcsolása stb..

	/**
	 * Prototípusban a nevük alapján jelennek meg a virológusok, ezt szimbolizálja a tagváltozó.
	 * A végleges verzióban majd komolyabb grafikus megjelenítést fog kapni, pl.: decorator minta elvei szerint
	 */
	private String name; //kiíratáshoz (grafikusnál majd decorator minta)

	/**
	 * Beállítja a virológus nevét.
	 * @param name A beállítandó név.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Kívülről elérhetővé teszi a virológus nevét.
	 * @return A virológus neve.
	 */
	public String getName() {
		return name;
	}

	/**
	 * A virológus támadási stratégiája.
	 */
	private IAttackStr attackStr;

	/**
	 * A támadási stratégia beállításáért felelős fv.
	 * @param a A beállítandó stratégia
	 */
	public void setAttackStr(IAttackStr a){
		attackStr = a;
	}

	/**
	 * A maximálisan birtokolható tárgyak száma.
	 */
	private final int maxNumberOfItems;

	public void setActionCount(int count){actionCount = count;}

	/**
	 *  Azt a mennyiseget tarolja, hogy mennyi lepest tud vegre hajtani a korben a virologus.
	 * */
	private int actionCount;

	private static final int MAX_ACTION_COUNT = 3;

	/**
	 *  A virologus altal birtokolt aminosav mennyiseget tarolja.
	 * */
	private int aminoAcid;

	/**
	 *  A virologus altal birtokolt nukleotid mennyiseget tarolja.
	 * */
	private int nucleotide;

	/**
	 *  A virologus altal maximum birtokolhato nukleotid es aminosav mennyiseget rogziti.
	 * */
	private int limit;


	/**
	 *  Azt a mezot tarolja ahol eppen a virologus tartozkodik.
	 * */
	private Field field;

	/**
	 * Az aktuális tartózkodási helyét adja vissza a virológusnak.
	 * @return Az említett mező
	 */
	public Field getField(){
		return field;
	}

	/**
	 * A Game osztaly peldanya ami a korok vezerlesehez szukseges
	 */
	private Game game;

	/**
	 *  Megtanult agensek listaja
	 */
	private final LinkedList<Agent> agents;

	/**
	 * Az eddigi megtanult genetikai kodok listaja
	 */
	private final ArrayList<GeneticCode> codes;


	/**
	 * Az aktuálisan birtokolt genetikai kódokat adja vissza.
	 * @return A birtokolt kódok.
	 */
	public List<GeneticCode> getGeneticCodes(){
		return codes;
	}

	/**
	 * A birtokolt felszerelesek listaja
	 */
	private final ArrayList<Equipment> equipments;

	/**
	 * A virologus mozgasi startegiajat tarolja.
	 */
	private IMoveStr moveStr;

	/**
	 * A virologus tanulasi startegiajat tarolja.
	 */
	private ILearnStr learnStr;

	/**
	 * Erre a virologusra felkenendo agensekkel kapcsolatos strategiat tarolja
	 */
	private IInjectedStr injectedStr;

	/**
	 * A virologus targy eldobasi strategiajat tarolja.
	 */
	private IDropStr dropStr;

	/**
	 * A virologus lootolasi strategiajat tarolja.
	 */
	private ILootStr lootStr;

	/**
	 * A virologus targyfelveteli startegiajat tarolja.
	 */
	private IEquipStr equipStr;

	/**
	 * A virologus anyag gyujtesi strategiajat tarolja.
	 */
	private ICollectStr collectStr;

	/**
	 * A virologus agensfelkenesi startegiajat tarolja.
	 */
	private IInjectStr injectStr;

	/**
	 * A virologus kirablasara vonatkozo startegiajat tarolja.
	 */
	private ILootedStr lootedStr;

	/**
	 * Az osztaly konstruktora, beallitja az alapertelmezett strategiakat.
	 */
	public Virologist(){
		equipments = new ArrayList<>();
		codes = new ArrayList<>();
		agents = new LinkedList<>();

		//from docs
		maxNumberOfItems = 3;
		limit = 20;
		aminoAcid = 0;
		nucleotide = 0;
		actionCount = MAX_ACTION_COUNT;

		reset(); //sets the default strategies
	}

	/**
	 * Beallitja a jatek osztaly peldanyanak referenciajat
	 * @param g a beallitando objektum
	 */
	public void setGame(Game g){
		game = g;
	}

	/**
	 * Eltávolítja a virológust a játékból.
	 */
	public void kill(){
		if(game.getCurrentPlayer().equals(this)) {
			this.endTurn();
		}

		field.removeVirologist(this);
		game.removeVirologist(this);
		notifyAllObservers();
	}

	/**
	 * Megtámad egy másik virológust, ha van elég akciópontja.
	 * @param v A virológus, akit megtámad.
	 */
	public void attack(Virologist v){
		if (actionCount > 0){
			attackStr.attack(this, v);
		}
		notifyAllObservers();
	}

	/**
	 * Olyan ágensfeleknési próbálkozást szimbolizál, amikor a virológust, egy ismert játékos próbálja meg felkenni.
	 * @param who A felkenő
	 * @param a A felkenni próbált ágens.
	 */
	public void targetedWith(Virologist who, Agent a){
		injectedStr.injected(who, this, a);
		notifyAllObservers();
	}

	/**
	 * Egy kívánt felszerelést dob ki a virológus.
	 * @param e A választott felszerelés
	 */
	public void removeEquipment(Equipment e){
		equipments.remove(e);
		notifyAllObservers();
	}

	/**
	 * A virologus random mozgasaert felel, ezt egy bizonyos agens valthatja ki.
	 */
	public void move()
	{
		if (actionCount > 0) {
			List<Field> fields = field.getNeighbours();

			if (!fields.isEmpty()) {
				move(fields.get(random.nextInt(fields.size())));
			}
		}
		notifyAllObservers();
	}

	/**
	 *  Ez a fuggveny kezdemenyezi a tartozkodasi mezorol valo elmozdulast,
	 *  meghivja IMoveStr-t ami elvegzi a tobbi fuggveny hivast a strategianak megfeleloen.
	 *
	 * @param field  Megadjuk azt a mezot amire elszeretnenk mozditani a virologust
	 */
	public void move(Field field)
	{
		if (actionCount > 0) {
			moveStr.move(this, this.field, field);
		}
	}

	/**
	 * Beallitja a fuggveny a parameterul kapott mezot, a tartozkodasi mezore
	 *
	 * @param f Az a mezo amire eppen atleptunk
	 */
	public void setField(Field f)
	{
		field = f;
	}

	/**
	 * A fuggveny az utolsónak felvett equipment eldobásáért felel
	 */
	public void drop()
	{
		if (actionCount > 0 && !equipments.isEmpty()) {
			dropStr.drop(this, field, equipments.remove(equipments.size()-1));
		}
	}

	/**
	 * A fuggveny a parameterul kapott virologustol valo aminosav lootolast kezdemenyezi,
	 * meghivja ILootStr fuggvenyet ami elvegzi a megfelelo fuggveny hivasokat.
	 *
	 * @param v A kivalaszott virologus akitol szeretnenk lootolni
	 */
	public void lootAminoAcidFrom(Virologist v)
	{
		if (actionCount > 0) {
			lootStr.lootAmino(this, v);
		}
	}

	/**
	 * A fuggveny a parameterul kapott virologustol valo aminosav lootolast kezdemenyezi,
	 * meghivja ILootStr fuggvenyet ami elvegzi a megfelelo fuggveny hivasokat.
	 *
	 * @param v @param v A kivalaszott virologus akitol szeretnenk lootolni
	 */
	public void lootNucleotideFrom(Virologist v)
	{
		if (actionCount > 0) {
			lootStr.lootNucleotide(this, v);
		}
	}

	/**
	 * A fuggveny a parameterul kapott virologustol valo aminosav lootolast kezdemenyezi,
	 * meghivja ILootStr fuggvenyet ami elvegzi a megfelelo fuggveny hivasokat.
	 *
	 * @param v A kivalaszott virologus akitol szeretnenk lootolni
	 */
	public void lootEquipmentFrom(Virologist v)
	{
		if (actionCount > 0 && equipments.size() < maxNumberOfItems) {
			lootStr.lootEquipment(this, v);
		}
	}

	/**
	 * Anyag felvetelt kezdemenyez az adott mezon, a strategia kezeli a megfelelo fuggveny hivasokat
	 */
	public void collect(Material material)
	{
		if (actionCount > 0) {
			collectStr.collect(this, field, material);
		}
	}

	/**
	 * Az adott mezon levo genetikai kod megtanulasat kezdemenyezi, ehhez meghivja a learnStr-t,
	 * ami elvegzi az allapotnak megfelelo fuggveny hivasokat.
	 */
	public void learn()
	{
		if (actionCount > 0) {
			learnStr.learn(this, field);
		}
	}

	/**
	 * Ez a fuggveny kezdemenyezi egy mezon levo felszereles felvetelet.
	 */
	public void equip()
	{
		if (actionCount > 0) {
			equipStr.equip(this, field);
		}
	}

	/**
	 * uj agenst helyezunk a taroloba
	 * @param a agens amit eltarolunk
	 */
	public void addAgent(Agent a)
	{
		if (agents.contains(a)){
			int i = agents.indexOf(a);
			Agent old = agents.get(i);
			agents.remove(i);
			if (old.getTtl() > a.getTtl()){
				a.setTtl(old.getTtl());
			}
		}
		agents.add(a);
	}

	/**
	 * Kivesz egy agenst a tarolobol
	 * @param a agens amit kiakarunk venni
	 */
	public void removeAgent(Agent a)
	{
		agents.remove(a);
	}

	/**
	 * A parameterben szereplo felszerelessel boviti, az eppen birtokolt felszereleseket sikeres lefutas eseten.
	 * @param e A felszerelest amit felvettunk
	 */
	public void addEquipment(Equipment e)
	{
		if (equipments.size() < maxNumberOfItems)
			equipments.add(e);
		e.apply(this);
		e.applyStrategy(this);
		notifyAllObservers();
	}

	/**
	 * Az equipment getter fuggvenye.
	 * @return Vissza ter a birtokolt felszerelesekkel, ha nincs akkor kivetelt dob
	 */
	public Equipment getEquipment() throws IndexOutOfBoundsException
	{
		if (equipments.isEmpty())
			throw new IndexOutOfBoundsException("ures a felszereles tarolo");

		return equipments.remove(equipments.size()-1);
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	/**
	 * A megtanult genetikai kodok listajat boviti
	 * @param code Az uj kod
	 */
	public void addGeneticCode(GeneticCode code)
	{
		if (!codes.contains(code))
			codes.add(code);
		notifyAllObservers();
	}

	/**
	 * egy masik virologus megkenese egy agenssel.
	 * @param v a masik virologus
	 * @param code az agens letrehozasahoz szukseges genetikai kod
	 */
	public void inject(Virologist v, GeneticCode code)
	{
		if (actionCount > 0)
		{
			injectStr.inject(this, v, code);
		}
	}

	/**
	 * Adott virologust lehet ezzel fuggvennyel  megcelozni egy agensfelkenessel.
	 * @param a Az az agens amit szeretnenk kenni
	 */
	public void targetedWith(Agent a)
	{
		injectedStr.injected(this, a);
		notifyAllObservers();
	}

	/**
	 * Kezdemenyezi aminosav levonasat a virologustol ami a strategianak megfeleloen tortenik
	 * @param self a virologus akitol levonjuk az aminosav mennyiseget
	 */
	public void stealAminoAcid(Virologist self)
	{
		lootedStr.lootedForAminoAcid(self, this);
		notifyAllObservers();
	}

	/**
	 * Kezdemenyezi nukleotid levonasat a virologustol ami a strategianak megfeleloen tortenik
	 * @param self a virologus akitol levonjuk a nukleotid mennyiseget
	 */
	public void stealNukleotid(Virologist self)
	{
		lootedStr.lootedForNukleotide(self, this);
		notifyAllObservers();
	}

	/**
	 * Veletlenszeruen kivalaszt egy felszerelest a meglevok kozul
	 * @param self a virologus aki elszeretne tulajdonitani a felszerelest
	 */
	public void stealEquipment(Virologist self)
	{
		if (!equipments.isEmpty()) {
			if(game.randOn) {
				int r = random.nextInt(equipments.size());
				Equipment e = equipments.get(r);
				lootedStr.lootedForEquipment(self, this, e);
			}
			else{
				lootedStr.lootedForEquipment(self,this, equipments.get(0));
			}
		}
		notifyAllObservers();
	}

	/**
	 * A torli az eddig megtanult genetikai kodokat.
	 */
	public void removeGeneticCodes()
	{
		codes.clear();
		notifyAllObservers();

	}

	/**
	 * Kiveszi a tarolobol az osszes agenst.
	 */
	public void removeAgents()
	{
		agents.clear();
		notifyAllObservers();
	}

	/**
	 * Drekementalja a koben vegrehajthato interakciok szamat
	 */
	public void decreaseActions()
	{
		if (actionCount > 0)
			actionCount--;
	}

	/**
	 * Tovabb adja a kort(atadja a lepesi jogot) a soron kovetkezo virologusnak
	 */
	public void endTurn()
	{
		actionCount = MAX_ACTION_COUNT;
		game.nextPlayer(codes.size());
		notifyAllObservers();
	}

	/**
	 * Megadja a virológus hátralévő lépésszámát
	 * @return hátralévő lépésszám
	 */
	public int getActionCount(){ return actionCount; }

	/**
	 * A parameter mertekevel noveli a birtokolt aminosav mennyiseget
	 * @param delta a mennyiseg amivel novelunk
	 */
	public void addAminoAcid(int delta)
	{
		aminoAcid+= delta;
		if (aminoAcid > limit) aminoAcid = limit;
	}

	/**
	 * A parameter mertekevel noveli a birtokolt nukleotid mennyiseget
	 * @param delta a mennyiseg amivel novelunk
	 */
	public void addNucleotide(int delta)
	{
		nucleotide+= delta;
		if (nucleotide > limit) nucleotide = limit;
	}

	/**
	 * A parameter mertekevel csokkenti a birtokolt nukleotid mennyiseget
	 * @param delta a mennyiseg amivel csokkentunk
	 * @throws VirologistException ha nincs megfelelo mennyiseg akkor kivetelt dobunk
	 */
	public void removeNucleotide(int delta) throws VirologistException
	{
		if(delta > nucleotide){
			throw new VirologistException("I don't have such many nucleotide!");
		}
		nucleotide -= delta;
	}

	/**
	 * A parameter mertekevel csokkenti a birtokolt aminosav mennyiseget
	 * @param delta a mennyiseg amivel csokkentunk
	 * @throws VirologistException ha nincs megfelelo mennyiseg akkor kivetelt dobunk
	 */
	public void removeAminoAcid(int delta) throws VirologistException
	{
		if(delta > aminoAcid){
			throw new VirologistException("I don't have such many amino acid!");
		}
		aminoAcid -= delta;
	}

	/**
	 * noveli a birtokolhato anyag(aminosav es nukleotid) mennyiseget
	 * @param delta noveles merteke
	 */
	public void increaseLimit(int delta)
	{
		limit += delta;
	}

	/**
	 * Csokkenti a birtokolhato anyag(aminosav es nukleotid) mennyiseget
	 * @param delta csokkentes merteke
	 */
	public void decreaseLimit(int delta)
	{
		limit -= delta;
		if (limit < 0) limit = 0;
	}

	/**
	 * Az aminosav mezo gettere
	 * @return A birtokolt aminosav mennyiseg
	 */
	public int getAminoAcid()
	{
		return aminoAcid;
	}

	/**
	 * A nulceotide mezo gettere
	 * @return A birtokolt nukleotid mennyisege
	 */
	public int getNucleotide()
	{
		return nucleotide;
	}

	public int getMaterialLimit() { return limit; }

	/**
	 * Frissiti a virologus strategiait
	 */
	public void update()
	{
		for (Agent agent : agents) {
			agent.update(this);
		}
	}

	/**
	 * Vissza strategiakat a default állapotba.
	 */
	public void reset()
	{
		lootedStr = new DefLooted();
		injectedStr = new DefInjected();
		collectStr = new DefCollect();
		equipStr = new DefEquip();
		lootStr = new DefLoot();
		dropStr = new DefDrop();
		injectStr = new DefInject();
		learnStr = new DefLearn();
		moveStr = new DefMove();
		attackStr = new DefAttack();

		for (Agent a:agents
		) {
			a.applyStrategy(this);
		}
		for (Equipment e :
				equipments) {
			e.applyStrategy(this);
		}
	}

	/**
	 * Beallitja a virologus IDropStr-jet
	 * @param d beallitando strategia
	 */
	public void setDropStr(IDropStr d)
	{
		dropStr = d;
	}

	/**
	 * Beallitja a virologus IMoveStr-jet
	 * @param m beallitando strategia
	 */
	public void setMoveStr(IMoveStr m)
	{
		moveStr = m;
	}

	/**
	 * Beallitja a virologus ILearnnStr-jet
	 * @param l beallitando strategia
	 */
	public void setLearnStr(ILearnStr l)
	{
		learnStr = l;
	}

	/**
	 * Beallitja a virologus ILootStr-jet
	 * @param l beallitando strategia
	 */
	public void setLootStr(ILootStr l)
	{
		lootStr = l;
	}

	/**
	 * Beallitja a virologus IInject-jet
	 * @param i beallitando strategia
	 */
	public void setInjectStr(IInjectStr i)
	{
		injectStr = i;
	}

	/**
	 * Beallitja a virologus IInjected-jet
	 * @param i beallitando strategia
	 */
	public void setInjectedStr(IInjectedStr i)
	{
		injectedStr = i;
	}

	/**
	 * Beallitja a virologus IEquipStr-jet
	 * @param e beallitando strategia
	 */
	public void setEquipStr(IEquipStr e)
	{
		equipStr = e;
	}

	/**
	 * Beallitja a virologus ICollectStr-jet
	 * @param c beallitando strategia
	 */
	public void setCollectStr(ICollectStr c)
	{
		collectStr= c;
	}

	/**
	 * Beallitja a virologus ILootedStr-jet
	 * @param l beallitando strategia
	 */
	public void setLootedStr(ILootedStr l)
	{
		lootedStr =l;
	}
}
