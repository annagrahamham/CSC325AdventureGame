public class Fisherman extends GameCharacter implements Runnable, Predator, Prey {
    private int attackDamage;
    private boolean defending;
    public Fisherman(String n, int h) {
        name = n;
        health = h;
        attackDamage = 25; // Default attack damage
        defending = false;
    }

    @Override
    public void act() {
        // Implement behavior for the fisherman
    }

    @Override
    public void attack(GameCharacter g) {
        g.health -= attackDamage;
    }
    @Override
    public void defenseUp() {
        defending = true;
    }

    @Override
    public void defenseDown() {
        defending = false;
    }

    @Override
    public void run() {
        // Implement behavior for the fisherman
    }
}