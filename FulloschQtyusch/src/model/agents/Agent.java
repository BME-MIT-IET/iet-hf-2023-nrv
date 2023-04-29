package model.agents;


import model.Virologist;

/**
 * Egy ágens, amely valamilyen hatással van a virológusra és ez időben elévül.
 * Élettartamát periodikusan frissíteni kell, ilyenkor mindig eggyel csökken.
 * Ha egyszer lejárt már többé nem lesz hatása.
 */
public abstract class Agent
{
	/**
	 * Az Object equals függvényének felülírása, összehasonlítja ezt az objektumot a paraméterül kapottal (mely jellemzően egy ágens).
	 * @param o az összehasonlítandó objektum.
	 * @return megegyezik-e a 2 objektum típusa.
	 */
	@Override
	public boolean equals(Object o){
		return this.getClass().getSimpleName().equals(o.getClass().getSimpleName());
	}

	/**
	 * @return az ágens típusa.
	 */
	public String getName(){
		return this.getClass().getSimpleName();
	}

	/**
	 * @return az ágens hátralévő hatásideje szöveg típusként.
	 */
	public String getTimeToLive(){return String.valueOf(timeToLive);}

	/**
 	 * @return az ágens hátralévő hatásideje.
	 */
	public int getTtl(){
		return timeToLive;
	}

	/**
	 * Beállítja az ágens hatásidejét.
	 * @param ttl a beállítandó élettartam.
	 */
	public void setTtl(int ttl){
		timeToLive = ttl;
	}

	/**
	 * Az ágens hátra lévő élettartama.
	 */
	protected int timeToLive;

	public Agent(int ttl){
		timeToLive = ttl;
	}

	/**
	 * Öregíti egy egységgel az Agent-et.
	 * Ha lejárt eltávolítja magát a virológusról, és reseteli azt.
	 * @param v a tulajdonos virológus.
	 */
	public void Update(Virologist v)
	{
		timeToLive--;
		if (timeToLive == 0){
			v.RemoveAgent(this);
			v.Reset();
		}
	}

	/**
	 * Default implementációban üres függvény, de azt reprezentálja, hogy az ágenst elhelyezik a paraméterül kapott virológuson
	 * és esetlegesen ennek vannak direkt/azonnali hatásai rá.
	 * @param v a célzott virológus.
	 */
	public void Apply(Virologist v)
	{
	}

	/**
	 * Az ágens hatásait hajtja végre a virológuson,
	 * átállítja bizonyos cselekvéseinek a működését,
	 * alapértelmezetten üres, amikor nincs az ágensnek hosszútávú hatása, csak azonnali.
	 * @param v a célzott virológus.
	 */
	public void ApplyStrategy(Virologist v)
	{
	}
}
