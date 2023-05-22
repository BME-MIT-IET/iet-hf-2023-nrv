package model.strategy;



import model.Virologist;
import model.agents.Agent;

/**
 * Az a felkenődés stratégia, mikor a virológusra nem engedi felkenődni az adott ágenst.
 */
public class NoInjected implements IInjectedStr
{
	/**
	 * A felkenődést nem engedélyezi v-re a stratégia, így nem történik semmi.
	 * @param v A virológus, akire felkenték az ágenst.
	 * @param a A felkent ágens.
	 */
	@Override
	public void Injected(Virologist v, Agent a)
	{
	}

	/**
	 * A felkenődést nem engedélyezi v-re a stratégia, így nem történik semmi.
	 * @param by A felkenő virológus.
	 * @param injected A felkent virológus.
	 * @param a A felkent ágens.
	 */
	@Override
	public void Injected(Virologist by, Virologist injected, Agent a) {
	}
}
