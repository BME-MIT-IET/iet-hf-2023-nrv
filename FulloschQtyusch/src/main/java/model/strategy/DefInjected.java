package model.strategy;



import model.Virologist;
import model.agents.Agent;

/**
 * A felkenés default stratégiája, mikor ténylegesen megtörténik a felkenés.
 */
public class DefInjected implements IInjectedStr
{
	/**
	 * A stratégia alkalmazásakor hívott metódus.
	 * @param v A virológus, akire felkenték az ágenst.
	 * @param a A felkent ágens.
	 */
	@Override
	public void injected(Virologist v, Agent a)
	{
		a.apply(v);
		v.addAgent(a);
		a.applyStrategy(v);
	}

	@Override
	public void injected(Virologist by, Virologist injected, Agent a) {
		injected(injected, a);
	}
}
