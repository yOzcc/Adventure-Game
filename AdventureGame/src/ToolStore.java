import java.util.Arrays;

public class ToolStore extends NormalLoc{

    public ToolStore(Player player) {
        super(player,"Tool store");
    }

    @Override
    public boolean onLocation(){
        System.out.println("");
        System.out.println("Welcome to the tool store.");
        boolean showMenu = true;
        while (showMenu){
            System.out.println();
        System.out.println("1 - Weapons");
        System.out.println("2 - Armors");
        System.out.println("3 - Exit");
        System.out.print("Your choose: ");
        int selectCase = Location.input.nextInt();
        while (selectCase<1 && selectCase<3){
            System.out.println("Please enter a valid value!");
            selectCase = input.nextInt();
        }
        switch (selectCase){
            case 1:
                printWeapon();
                buyWeapon();
                break;
            case 2:
                printArmor();
                buyArmor();
                break;
            case 3:
                System.out.println("We look forward to seeing you again.");
                showMenu = false;
                break;
            }
        }
        return true;
    }

    public void printWeapon(){
        System.out.println("");
        System.out.println("--------------- Weapons ---------------");
        for (Weapon w : Weapon.weapons()){
            System.out.println(w.getId() + " - " + w.getName() +
                    "\t Price : [ " + w.getPrice() + " ]" +
                    "\t Damage : [ " + w.getDamage() + " ]");
        }
        System.out.println("0 - Exit");
    }

    public void buyWeapon(){
        System.out.print("Please choose your weapon: ");

        int selectWeaponId = input.nextInt();
        while (selectWeaponId < 0 && selectWeaponId > Weapon.weapons().length){
            System.out.println("Please enter a valid value!");
            selectWeaponId = input.nextInt();
        }
        if (selectWeaponId !=0){
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponId);

            if (selectedWeapon != null){
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("You do not have enough money.");
                }else {
                    System.out.println(selectedWeapon.getName() + " successfully purchased!");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("New Balance: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }
    }

    public void printArmor(){
        System.out.println("");
        System.out.println("------------------ Armors ------------------");
        for (Armor a : Armor.armors()){
            System.out.println(a.getId() + " - " + a.getName() +
                    "\tPrice : [ " + a.getPrice() + " ]" +
                    "\tArmor value : [ " + a.getBlock() + " ]");
        }
        System.out.println("0 - Exit");
    }

    public void buyArmor(){
        System.out.print("Please choose your armor: ");

        int selectArmorId = input.nextInt();
        while (selectArmorId < 0 && selectArmorId > Armor.armors().length) {
            System.out.println("Please enter a valid value!");
            selectArmorId = input.nextInt();
        }

        if (selectArmorId !=0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorId);

            if (selectedArmor != null){
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("You do not have enough money.");
                }else {
                    System.out.println(selectedArmor.getName() + " armor successfully purchased!");
                    this.getPlayer().setMoney(this.getPlayer().getMoney()- selectedArmor.getPrice());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("New Balance: " + this.getPlayer().getMoney());
                    System.out.println();

                }
            }
        }
    }
}
