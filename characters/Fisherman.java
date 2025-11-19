package characters;
import game.GameWorld;
import java.util.Random;

public class Fisherman extends GameCharacter implements Runnable, Predator, Prey {
    private final int attackDamage;
    private boolean defending;
    private final Random random;
    private int fishCaught;
    
    public Fisherman(String n, int h) {
        name = n;
        health = h;
        attackDamage = 25; // Default attack damage
        defending = false;
        random = new Random();
        fishCaught = 0;
    }

    @Override
    public void act() {
        // random fisherman behavior
        int action = random.nextInt(4);
        try {
            switch(action) {
                case 0 -> {
                    System.out.println(name + " casts the fishing net into the water.");
                    Thread.sleep(1500 + random.nextInt(2000));
                    int catchChance = random.nextInt(100);
                    defenseDown();
                    if(catchChance < 50) {
                        int caught = random.nextInt(3) + 1;
                        fishCaught += caught;
                        System.out.println(name + " caught " + caught + " fish! Total: " + fishCaught);
                    } else {
                        System.out.println(name + " caught nothing this time.");
                    }
                }
                // chance for defense and health to go up 
                case 1 -> {
                    System.out.println(name + " repairs fishing equipment.");
                    Thread.sleep(2000);
                    heal(5);
                    defenseUp();
                }
                //chance for defense to drop
                case 2 -> {
                    System.out.println(name + " navigates to a new fishing spot.");
                    Thread.sleep(1200);
                    defenseDown();

                }
                case 3 -> {
                    System.out.println(name + " checks weather conditions and rests.");
                    Thread.sleep(1800);
                    heal(3);
                    defenseDown();
                }
            }
            
            // Random challenges and events
            if(random.nextInt(100) < 15) {
                System.out.println(name + " encounters a storm! Equipment damaged!");
                if(defending) {
                    System.out.println(name + " weathers the storm successfully!");
                } else {
                    System.out.println(name + " takes damage from the storm!");
                    heal(10);
                }
                
                Thread.sleep(2000);
            }
            
            if(random.nextInt(100) < 10) {
                System.out.println(name + " discovers a school of fish!");
                fishCaught += 5;
                Thread.sleep(1000);
            }
            
            if(random.nextInt(100) < 8) {
                System.out.println(name + "'s boat has a leak!");
                heal(5);
                Thread.sleep(1000);
            }
            
            // The Fisherman might encounter and defend against predators
            if(random.nextInt(100) < 12) {
                GameCharacter threat = GameWorld.getRandomCharacter();
                if(threat != null && threat != this && GameWorld.isAlive(threat)) {
                    System.out.println(name + " spots " + threat.name + " approaching the boat!");
                    defenseUp();
                    Thread.sleep(800);
                    attack(threat);
                    defenseDown();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    //attack method for character interactions
    @Override
    public void attack(GameCharacter g) {
       synchronized(g) {
        System.out.println(name + " uses fishing tools to attack " + g.name);
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
    public boolean isDefending() {
        return defending;
    }

    @Override
    public void run() {
        System.out.println(name + " the Fisherman sets out to sea...");
        try {
            for(int i = 0; i < 7; i++) {
                act();
                Thread.sleep(600);
                if(health <= 0) {
                System.out.println(name + " DIED AT SEA!!! HIS FAMILY WILL MISS HIM!!!");
                    break;
                }
                if(fishCaught >= 20) {
                    System.out.println(name + " is heading back to shore.");
                    break;
                }
            }
            System.out.println(name + " returns to port. Fish caught: " + fishCaught + ", Health: " + health);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}