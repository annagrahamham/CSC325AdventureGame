package game;

import characters.Fisherman;
import characters.Orca;
import characters.Seal;
import java.util.List;

public class GameEngine {
    public static void main(String[] args) {
        
        // Create characters
        Orca orca = new Orca("The Orca", 100);
        Seal seal = new Seal("The Seal", 80);
        Fisherman fisherman = new Fisherman("Captain Jack", 100);
        
        // register characters with GameWorld for interactions
        GameWorld.registerCharacter(orca);
        GameWorld.registerCharacter(seal);
        GameWorld.registerCharacter(fisherman);

        // Create threads for each character
        List<Thread> threads = List.of(
            new Thread(orca, "orcaThread"),
            new Thread(seal, "sealThread"),
            new Thread(fisherman, "fishermanThread")
        );
        // used lambda to start and join threads vvv
        // Start all threads simultaneously
        threads.forEach(Thread::start);

        // Wait for all threads to complete
        threads.forEach(t -> {
            try { t.join(); } 
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        // display
        System.out.println("Final Status:");
        //used lambda to print final health of all characters in one line
        List.of(orca, seal, fisherman) 
            .forEach(c -> { System.out.println("  " + c.getName() + " - Health: " + c.getHealth());});
    }

}