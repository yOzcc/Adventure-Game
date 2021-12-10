import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Snake extends Obstacle{

    private static int snakeDamage = (int) (Math.random() * (6 - 3)) + 3;


    public Snake() {
        super(4, "Snake",snakeDamage,12,0);
    }

    public static int getSnakeDamage() {
        return snakeDamage;
    }

    public static void setSnakeDamage(int snakeDamage) {
        Snake.snakeDamage = snakeDamage;
    }
}
