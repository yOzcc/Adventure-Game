public class SafeHouse extends NormalLoc{
    public SafeHouse(Player player) {
        super(player,"Safe house");
    }

    @Override
    public boolean onLocation(){
        System.out.println("");
        System.out.println("You are in the safe house.");
        System.out.println("Your health is renewed.");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());

        return true;
    }
}
