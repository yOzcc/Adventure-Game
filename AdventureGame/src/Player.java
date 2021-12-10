import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int originalHealth;
    private int money;
    private String name;
    private String charName;
    private Inventory inventory;
    private String[] awardList = new String[3];

    Scanner input = new Scanner(System.in);

    public Player(String name){
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar(){
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (GameChar gameChar : charList){
            System.out.println("ID :  [ "+ gameChar.getId()
                    + " ]  Character :  [ " + gameChar.getName()
                    + " ]\t Damage  ->  [ " + gameChar.getDamage()
                    + " ]\t Health  ->  [ " + gameChar.getHealth()
                    + " ]\t Money  ->  [ " + gameChar.getMoney() + " ]");
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.print("Please choose your hero: ");
        int selectChar = input.nextInt();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        /*
        System.out.println("Your hero : [ " + this.getCharName()
                + " ]\t Damage  ->  [ " + this.getDamage()
                + " ]\t Health  ->  [ " + this.getHealth()
                + " ]\t Money  ->  [ " + this.getMoney() + " ]");
        */
    }

    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public boolean finishGame(){
        if (this.awardList[2]!= null){
            return true;
        }
        return false;
    }

    public boolean isCaveComplete(){
        for (int i = 0 ; i<3 ; i++){
            if (this.awardList[i] == "Food"){
                return true;
            }
        }
        return false;
    }

    public boolean isForestComplete(){
        for (int i = 0 ; i<3 ; i++){
            if (this.awardList[i] == "Firewood"){
                return true;
            }
        }
        return false;
    }

    public boolean isRiverComplete(){
        for (int i = 0 ; i<3 ; i++){
            if (this.awardList[i] == "Water"){
                return true;
            }
        }
        return false;
    }

    public void printInfo(){
        System.out.println("Weapon  ->  [ " + this.getInventory().getWeapon().getName()
                + " ]\t Armor  ->  [ " + this.getInventory().getArmor().getName()
                + " ]\t Armor value  ->  [ " + this.getInventory().getArmor().getBlock()
                + " ]\t Damage  ->  [ " + this.getTotalDamage()
                + " ]\t Health  ->  [ " + this.getHealth()
                + " ]\t Money  ->  [ " + this.getMoney() + " ]");
    }

    public void addAward(String award){
        for (int i=0 ; i<3 ; i++){
            if (this.awardList[i] == null){
            this.awardList[i] = award;
                System.out.print("Your award inventory:\t");
                System.out.println(Arrays.toString(awardList));
            break;
            }else{
            }
        }
    }

    public int getTotalDamage(){
    return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage(){return damage;}
    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        if (health < 0){
            health = 1;
        }
        this.health = health;
    }

    public int getMoney(){
        return money;
    }
    public void setMoney(int money){
        this.money = money;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getCharName(){
        return charName;
    }
    public void setCharName(String charName){
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }
    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }
}
