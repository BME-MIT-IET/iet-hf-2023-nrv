package model.agents;


import model.Virologist;
import model.strategy.NoInjected;

/**
 * Olyan ágens, ami hatástalanítja az összes aktuálisan aktív ágenst a felkent virológuson
 */
public class Block extends Agent
{

	/**
	 * Konstruktor, amely beállítja az ágens hatásának hátralévő idejét.
	 * @param tL a beállítandó hatásidő
	 */
	public Block(int tL){
		super(tL);
	}

	/**
	 * Törli az összes jelenleg hatással bíró ágenst és hatásait a virológusból.
	 * @param v a célzott virológus
	 */
	@Override
	public void apply(Virologist v)
	{
		v.removeAgents();
	}

	/**
	 * Beállítja a virológuson, hogy blokkolja majd az eljövendő felkenéseket
	 * @param v a célzott virológus
	 */
	@Override
	public void applyStrategy(Virologist v)
	{
		NoInjected ni = new NoInjected();
		v.setInjectedStr(ni);
	}
}
