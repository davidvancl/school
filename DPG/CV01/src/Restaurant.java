import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Restaurant {
    private final ArrayList<Waiter> waitersFront = new ArrayList<>();
    ThreadPoolExecutor executor;
    private Integer unfinishedCustomers = 5;

    public Restaurant(){
        System.out.printf("======%s======\n","GUSTAVO RESTAURANT");

        for (int i = 0; i <= 7; i++) {
            waitersFront.add(new Waiter(i));
        }
        System.out.printf(" - %s\n","waiters: 7 / 7");
        System.out.printf(" - %s\n","table: 5 / 5");

        int processors = Runtime.getRuntime().availableProcessors();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(processors);
    }

    public synchronized void addGuest(Customer customer){
        System.out.printf("Zákazník byl usazen ke stolu. Přiřazené ID: %d \n", customer.getId());
        this.process(customer);
    }

    private void process(Customer customer) {
        executor.submit(() -> {
            String meal = customer.getRequestedMeal();
            Waiter waiter = assignWaiter();

            try {
                meal = waiter.take(meal);
                meal = waiter.serve(meal, customer);

                executor.submit(() -> {
                    try {
                        waiter.rest();
                        waitersFront.add(waiter);
                        notifyAll();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

                customer.eat(meal);

                if (customer.hasContinue()){
                    process(customer);
                } else {
                    System.out.printf("Zákazník(id: %d) se najedl a odchází. \n", customer.getId());
                    unfinishedCustomers = unfinishedCustomers - 1;

                    if (unfinishedCustomers == 0) {
                        System.out.printf("======%s======\n","GUSTAVO RESTAURANT");
                        executor.shutdown();
                    }
                }
            } catch (Exception e) {
                System.out.printf("[ERROR] %s\n", e.getMessage());
            }
        });
    }

    public synchronized Waiter assignWaiter(){
        while (waitersFront.isEmpty()) {
            try {
                System.out.println("Není volný žádný číšník. Čekám ...");
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return waitersFront.removeFirst();
    }
}
