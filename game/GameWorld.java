package game;
import characters.GameCharacter;
import characters.Prey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//manages interactions between characters so that random events can happen that involve multiple characters
public class GameWorld {
    private static final List<GameCharacter> allCharacters = new ArrayList<>();
    private static final List<GameCharacter> preyCharacters = new ArrayList<>();
    private static final Random random = new Random();
    
    //register characters to the game world
    public static void registerCharacter(GameCharacter character) {
        allCharacters.add(character);
        if(character instanceof Prey) {
            preyCharacters.add(character);
        }
    }
    //get random prey character that is alive (for attack method and orca hunting)
    public static GameCharacter getRandomPrey() {
        List<GameCharacter> alivePrey = preyCharacters.stream() //stream to filter alive prey characters
            .filter(c -> isAlive(c))
            .toList(); 
        return alivePrey.isEmpty() ? null : alivePrey.get(random.nextInt(alivePrey.size()));
    }
    // get random character that is alive (for attack method )
    public static GameCharacter getRandomCharacter() {
        List<GameCharacter> aliveCharacters = allCharacters.stream() //stream to filter alive characters
            .filter(c -> isAlive(c))
            .toList(); 
        return aliveCharacters.isEmpty() ? null : aliveCharacters.get(random.nextInt(aliveCharacters.size()));
    }
    
    //check if character is alive
    public static synchronized boolean isAlive(GameCharacter character) {
        return character.getHealth() > 0;
    }
}
