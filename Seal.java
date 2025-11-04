public class Seal extends GameCharacter implements Runnable, Prey {
    private boolean defending;
    public Seal(String n, int h){
        name = n;
        health = h;
        defending = false;
    }

    @Override
    public void defenseUp(){
        defending = true;
    }

    @Override
    public void defenseDown(){
        defending = false;
    }

    @Override
    public void act(){
      //implement behavior
    }

    @Override
    public void run(){
      //implement behavior
    }
}