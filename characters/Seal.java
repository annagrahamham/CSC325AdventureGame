package characters;
import java.util.Random;

public class Seal extends GameCharacter implements Runnable, Prey {
    private boolean defending;
    private final Random random;
  
    
    public Seal(String n, int h){
        name = n;
        health = h;
        defending = false;
        random = new Random();
    
    }



    @Override
    public void act(){
        // Seal behavior: forage, hide, or socialize
        int action = random.nextInt(2);
        try {
            switch(action) {
                case 0 -> {
                    //chance to regain health while foraging
                    System.out.println(name + " searches for fish near the shore.");
                    Thread.sleep(1000 + random.nextInt(1500));
                    if(random.nextInt(100) < 40) {
                        System.out.println(name + " found food! Health restored.");
                        heal( 15);
                        defenseDown();
                    }
                }
                case 1 -> {
                    //chance to avoid predators while hiding
                    System.out.println(name + " discovers a safe cove.");
                    defenseUp();
                    Thread.sleep(500);
                }
                case 2 -> {
                    //chance for preys defense to drop while socializing
                    System.out.println(name + " plays with other seals.");
                    Thread.sleep(1200);
                    defenseDown();
                }
            }
            
            // Random events and challenges
            if(random.nextInt(100) < 20) {
                System.out.println(name + " spots a predator nearby!");
                if (defending) {
                    System.out.println(name + " successfully evades the predator!");
                } else {
                    System.out.println(name + " gets hurt by the predator!");
                    heal(3);
                    Thread.sleep(1000);
                }
            }
            
          
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void defenseUp(){
        defending = true;
      
    }

    @Override
    public void defenseDown(){
        defending = false;
    }
    
    //to be used in any attack logic 
    @Override
    public boolean isDefending() {
        return defending;
    }

    @Override
    public void run(){
        System.out.println(name + " begins its day in the ocean!");
        try {
            for(int i = 0; i < 6; i++) {
                act();
                Thread.sleep(400);
                if(health <= 0) {
                    System.out.println(name + " DIED!!!");
                    break;
                }
            }
            System.out.println(name + " ends its day. Final health: " + health);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}