class Orca extends GameCharacter implements Runnable, Predator {
private int attackDamage;

    public Orca(String n, int h){
        name = n;
        health = h;
        attackDamage = 30; //default attack
    }
    @Override
public void act(){
    //implement behavior
}

@Override
public void attack(GameCharacter g){
    System.out.println(name + " the Orca attacks " + g.name);
    g.health -= attackDamage;
}

@Override
public void run(){
// implement behavior
}
}