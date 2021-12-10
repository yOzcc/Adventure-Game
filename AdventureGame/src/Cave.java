public class Cave extends BattleLoc{
    String food;
    public Cave(Player player) {
        super(player, "Cave", new Zombie(),"Food" ,3);
    }
}
