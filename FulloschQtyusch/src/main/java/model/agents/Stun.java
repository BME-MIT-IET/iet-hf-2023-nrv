package model.agents;


import model.Virologist;
import model.strategy.*;

/**
 * Olyan ágens, ami lebénítja a virológust, megafosztja minden körében a végezhető tevékenységtől,
 * így csak át tudja majd adni a körét a következő játékosnak.
 */
public class Stun extends Agent
{

	/**
	 * Konstruktor, amely beállítja az ágens hatásának hátralévő idejét.
	 * @param ttl a beállítandó hatásidő
	 */
	public Stun(int ttl){
		super(ttl);
	}

	/**
	 * Beállítja a célzott virológuson a módosított viselkedést -stratégiát-.
	 * Azt, hogy semmit se tudjon tenni, őt se lehessen megkenni, de ki tudják fosztani más játékosok.
	 * @param v a célzott virológus
	 */
	@Override
	public void applyStrategy(Virologist v)
	{
		Looted lt = new Looted();
		NoCollect nc = new NoCollect();
		NoDrop nd = new NoDrop();
		NoEquip ne = new NoEquip();
		NoInject ni = new NoInject();
		NoInjected nitd = new NoInjected();
		NoLearn nlrn = new NoLearn();
		NoLoot nl = new NoLoot();
		NoMove nm = new NoMove();

		v.setInjectedStr(nitd);
		v.setCollectStr(nc);
		v.setDropStr(nd);
		v.setEquipStr(ne);
		v.setInjectStr(ni);
		v.setLearnStr(nlrn);
		v.setLootedStr(lt);
		v.setLootStr(nl);
		v.setMoveStr(nm);
	}
}
