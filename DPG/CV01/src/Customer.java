import java.util.ArrayList;

public class Customer {
    private final ArrayList<String> meals;
    private final Integer id;

    public Customer(Integer id, ArrayList<String> meals) {
        this.id = id;
        this.meals = meals;
    }

    public Integer getId(){
        return this.id;
    }

    public String getRequestedMeal(){
        String meal = meals.removeFirst();
        System.out.printf("Zakazník(id: %d) požaduje: %s\n", this.id, meal);
        return meal;
    }

    public boolean hasContinue(){
        return !meals.isEmpty();
    }

    public void eat(String meal) throws InterruptedException {
        System.out.printf("Zakazník(id: %d) jí jídlo %s\n", this.id , meal);
        Thread.sleep(1000);
    }
}
