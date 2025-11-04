public class GameEngine {
    public static void main(String[] args) {
        Orca orca = new Orca("Willy", 100);
        Seal seal = new Seal("Sammy", 80);

        orca.attack(seal);
        System.out.println(seal.name + "'s health after attack: " + seal.health);
    }

}