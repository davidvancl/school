public class Waiter {
    private final Integer id;

    public Waiter(Integer id){
        this.id = id;
    }

    public String take(String meal) throws InterruptedException {
        System.out.printf("Číšník(id: %d) bere jídlo: %s \n", this.id, meal);
        Thread.sleep(100);
        return meal;
    }

    public String serve(String meal, Customer customer) throws InterruptedException {
        System.out.printf("Číšník(id: %d): servíruje jídlo %s pro zákazníka(id: %d) \n", this.id, meal, customer.getId());
        Thread.sleep(100);
        return meal;
    }

    public void rest() throws InterruptedException {
        System.out.printf("Číšník(id: %d): odpočívá ...\n", this.id);
        Thread.sleep(500);
    }
}
