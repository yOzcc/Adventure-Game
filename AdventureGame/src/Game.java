import java.util.Locale;
import java.util.Scanner;

public class Game {
    Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("Welcome to the Adventure Game ! ");
        System.out.print("Please enter your name : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Welcome " + player.getName() + "! Many challenges await you here...");
        System.out.println("I wish you success in this difficult and dark journey.\n");
        System.out.println("You must choose a character to start this journey.");
        player.selectChar();

        Location location = null;
        while (true){
            System.out.println("");
            player.printInfo();
            System.out.println("");
            System.out.println("Locations : ");
            System.out.println("1 - Safe House");
            System.out.println("2 - Tools Store");
            System.out.println("3 - Go to the Cave   -Battle-   Award : [Food]");
            System.out.println("4 - Go to the Forest   -Battle-   Award : [Firewood]");
            System.out.println("5 - Go to the River   -Battle-   Award : [Water]");
            System.out.println("6 - Go to the Quarry   -Battle-   Award : Random -> [Money or Item]");
            System.out.println("0 - Exit the game");
            System.out.print("Please select the region you want to go to: ");
            int selectLoc = input.nextInt();
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    if (player.finishGame() == true) {
                        System.out.println("Congratulations. You have successfully completed this challenging journey.");
                        System.out.print("Do you want to end the game?  Yes -> [Y] , No -> [N] || Your select: ");
                        Scanner select = new Scanner(System.in);
                        String selectStatus = select.next().toUpperCase(Locale.ROOT);
                        if (selectStatus.equals("Y")) {
                            location = null;
                            break;
                        } else{
                        }
                    }
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if (player.isCaveComplete() == true){
                        System.out.println("You cannot enter the cave; because you have completed this zone.");
                        System.out.println("You are directed to the safe area...");
                        location = new SafeHouse(player);
                        break;
                    } else{
                        location = new Cave(player);
                    }break;
                case 4:
                    if (player.isForestComplete() == true) {
                        System.out.println("You cannot enter the forest; because you have completed this zone.");
                        System.out.println("You are directed to the safe area...");
                        location = new SafeHouse(player);
                        break;
                    } else {
                        location = new Forest(player);
                    }break;
                case 5:
                    if (player.isRiverComplete() == true) {
                        System.out.println("You cannot enter the river; because you have completed this zone.");
                        System.out.println("You are directed to the safe area...");
                        location = new SafeHouse(player);
                        break;
                    } else {
                        location = new River(player);
                    }break;
                case 6:
                    location = new Quarry(player);
                    break;

                default:
                    System.out.println("Please enter a valid location! ");
            }

            if (location == null){
                System.out.println("The game has been terminated.\nWe are waiting for you again...");
                break;
            }
            if (!location.onLocation()){
                System.out.println("Game over!");
                break;
            }

        }

    }
}
