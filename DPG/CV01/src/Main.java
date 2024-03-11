import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        ArrayList<String> mealTypes = new ArrayList<>();
        mealTypes.add("Polévka");
        mealTypes.add("Hlavní chod");
        mealTypes.add("Dezert");

        for (int i = 0; i <= 4; i++) {
            restaurant.addGuest(new Customer(i, new ArrayList<>(mealTypes)));
        }
    }
}