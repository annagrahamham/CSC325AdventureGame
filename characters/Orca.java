package characters;
import game.GameWorld;
import java.util.Random;

public class Orca extends GameCharacter implements Runnable, Predator {
private final int attackDamage;
private final Random random;

    public Orca(String n, int h){
        name = n;
        health = h;
        maxHealth = h;
        attackDamage = 40; //default attack
        random = new Random();
    }
    
    @Override
public void act(){
    // Orca behavior: hunt, rest, or patrol
    int action = random.nextInt(3);
    try {
        switch(action) {
            // Searching for prey (does nothing special)
            case 0 -> {
                System.out.println(name + " is searching for prey... Energy: " + health);
                Thread.sleep(1000 + random.nextInt(2000));
            }
            // Hunting for prey, giving chance to attack
            case 1 -> {
                System.out.println(name + " is hunting! Energy: " + health);
                GameCharacter prey = GameWorld.getRandomPrey();
                if(prey != null && prey != this && GameWorld.isAlive(prey)) {
                    attack(prey);
                } else {
                    System.out.println(name + " found no suitable prey nearby.");
                }
                Thread.sleep(1500);
                if(random.nextInt(100) < 30) {
                    System.out.println(name + " encountered a challenge: strong currents!");
                    health -= 20;
                }
            }
            case 2 -> {
                System.out.println(name + " surfaces to breathe and rest. Energy: " + health);
                Thread.sleep(2000);
                heal(3);
            }
        }
        
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

@Override
public void attack(GameCharacter g){
     synchronized(g) {
        System.out.println(name + " attacks " + g.name);

        //sees if prey is defending at all and fails attack if so 
        if (g instanceof Prey prey) { 
            if (prey.isDefending()) {
            System.out.println(g.name + " defends against the attack!");
        } else {
            g.health -= attackDamage;
        }
    } else {
            g.health -= attackDamage;
        }
       
    }
 try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    // add random storyline element
    try {
        if(random.nextInt(100) < 40) {
            System.out.println("The prey fights back! " + name + " takes damage.");
            health -= 20;
            Thread.sleep(800);
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

@Override
public void run(){
    System.out.println(name + " begins its ocean journey!");
    try {
        for(int i = 0; i < 5; i++) {
            act();
            Thread.sleep(500);
            if(health <= 0) {
                System.out.println(name + " DIED!!!");
                break;
            }
        }
        System.out.println(name + " completes its hunting cycle. Final health: " + health);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
}