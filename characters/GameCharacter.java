package characters;
public abstract class GameCharacter{
	protected String name;
	protected volatile int health; // "volatile" ensures visibility across threads
	protected int maxHealth;

	public abstract void act();
	public abstract void run();

	public String getName(){
		return name;
	}
	public int getHealth() {
		return health;
	}
	// healing to not exceed maxHealth (same across all subclasses)
	protected void heal(int amount) {
		if (amount <= 0) return; //checks that amount passed is positive
		int newHealth = this.health + amount; 
        if (newHealth > maxHealth) {
            this.health = maxHealth;
        } else {
            this.health = newHealth;
        }
	}
}