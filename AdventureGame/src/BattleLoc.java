import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public abstract class BattleLoc extends Location{
    Scanner selectAnswer = new Scanner(System.in);

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("");
        System.out.println("You are here now: " + this.getName());
        System.out.println("Be careful. " + obsNumber + " " +this.getObstacle().getName() + " lives here!");
        System.out.println("Will you fight or will you run away?");
        System.out.println("Fight -> [F] , Run away -> [R]");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("F") && combat(obsNumber)){
                System.out.println("You have defeated all the enemies in the " + this.getName() + "!");
                if (this.getObstacle().getName() != "Snake"){
            System.out.println("You earned " + this.getAward());
            this.getPlayer().addAward(this.getAward());
                return true;
            }
        }

        if (this.getPlayer().getHealth() <= 0 ){
            System.out.println("You died! ");
            return false;
        }

        return true;
    }

    public boolean combat(int obsNumber){
        for (int i=1 ; i<=obsNumber ; i++){
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println();
                System.out.println("Will you hit or will you run away?");
                System.out.println("Hit -> [H] , Run away -> [R]");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("H")){
                    System.out.println("You hit! ");
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getObstacle().getHealth() > 0 ){
                        System.out.println();
                        System.out.println("The obstacle hit you: ");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                }else {return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("You have defeated the enemy.");
                if (this.getObstacle().getName() == "Snake"){
                    this.randomAward();
                }
                System.out.println("You have earned "+this.getObstacle().getAward()+ " gold.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Your current money: " + this.getPlayer().getMoney());
            }else{
                return false;
            }
        }
        return true;
    }

    public void afterHit(){
        System.out.println("Your health: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() +"'s health: "+ this.getObstacle().getHealth());
        System.out.println("-------");
    }

    public void playerStats(){
        System.out.println("----------------------------------");
        System.out.println("Player values:\n----------------------------------");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Armor: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Armor value: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Money: " + this.getPlayer().getMoney());
        System.out.println();

    }

    public void obstacleStats(int i){
        System.out.println("----------------------------------");
        System.out.println(i + ". " + this.getObstacle().getName() + " values:\n----------------------------------");
        System.out.println("Health: " + this.getObstacle().getHealth());
        System.out.println("Damage: " + this.getObstacle().getDamage());
        System.out.println("Award: " + this.getObstacle().getAward());
        System.out.println();
    }

    public int randomAward() {
        Random rnd = new Random();
        int number = rnd.nextInt(100) + 1;
        if (number>=1 && number<=15){
            //damage item
            int damNumber = rnd.nextInt(100) + 1;
            if (damNumber>=1 && damNumber<=20){
                System.out.println("You earned : Rifle.");
                System.out.print("Do you want to use this weapon?\t ||\tYes -> [Y] , No -> [N]");
                String select = selectAnswer.nextLine().toUpperCase();
                if (select.equals("Y")){
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(3));
                }else {}

            }
            if (damNumber>=21 && damNumber<=50){
                System.out.println("You earned : Sword.");
                System.out.print("Do you want to use this weapon?\t ||\tYes -> [Y] , No -> [N]");
                String select = selectAnswer.nextLine().toUpperCase();
                if (select.equals("Y")){
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(2));
                }else {}
            }
            if (damNumber>51 && damNumber<=100){
                System.out.println("You earned : Gun");
                System.out.print("Do you want to use this weapon?\t ||\tYes -> [Y] , No -> [N]");
                String select = selectAnswer.nextLine().toUpperCase();
                if (select.equals("Y")){
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(1));
                }else {}
            }
        }
        if (number>=16 && number<=30){
            //defence item
            int defNumber = rnd.nextInt(100) + 1;
            if (defNumber>=1 && defNumber<=20){
                System.out.println("You earned : Heavy armor.");
                System.out.print("Do you want to use this armor?\t ||\tYes -> [Y] , No -> [N]");
                String select = selectAnswer.nextLine().toUpperCase();
                if (select.equals("Y")){
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(3));
                }else {}
            }
            if (defNumber>=21 && defNumber<=50){
                System.out.println("You earned : Medium armor.");
                System.out.print("Do you want to use this armor?\t ||\tYes -> [Y] , No -> [N]");
                String select = selectAnswer.nextLine().toUpperCase();
                if (select.equals("Y")){
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(2));
                }else {}
            }
            if (defNumber>51 && defNumber<=100){
                System.out.println("You earned : Light armor.");
                System.out.print("Do you want to use this armor?\t ||\tYes -> [Y] , No -> [N]");
                String select = selectAnswer.nextLine().toUpperCase();
                if (select.equals("Y")){
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(1));
                }else {}
            }
        }
            if (number>=31 && number<=55){
                //money
                int monNumber = rnd.nextInt(100) + 1;

                if (monNumber>=1 && monNumber<=20){
                    this.getObstacle().setAward(10);
                }
                if (monNumber>=21 && monNumber<=50){
                    this.getObstacle().setAward(5);
                }
                if (monNumber>=51 && monNumber<=100){
                    this.getObstacle().setAward(1);
                }
                if (number>=56 && number<=100){
                    //nothing
                    this.getObstacle().setAward(0);
                }
            }
            if (number>=56 && number<=100){
                System.out.println("You didn't earn nothing.");
            }
            return 0;
        }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle())+1;
    }
    public Obstacle getObstacle() {
        return obstacle;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
