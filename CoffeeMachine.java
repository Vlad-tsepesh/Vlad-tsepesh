package machine;

import java.sql.SQLOutput;
import java.util.Scanner;

public class CoffeeMachine {
    static int mess = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Resources resources = new Resources();

        while(true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String input = (!scanner.hasNextInt()) ? scanner.nextLine() : scanner.next();
            switch (input) {
                case "buy":
                    if (mess < 10){
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                        buy(resources, scanner);
                    }
                    else System.out.println("I need cleaning!");
                    break;
                case "fill":
                    fill(resources, scanner);
                    break;
                case "take":
                    System.out.printf("I gave you $%d\n\n", resources.getMoney());
                    resources.setMoney(resources.getMoney() * -1);
                    break;
                case "remaining":
                    resources.getInfo();
                    break;
                case "cleaning":
                    System.out.println("I have been cleaned!");
                    mess = 0;
                    break;
                case "exit":
                    return;
            }
        }
    }

    private static void makeCoffee(Resources stock, Coffee coffee) {
        if (stock.getWater() < coffee.getWater())
            System.out.println("Sorry, not enough water!");
        else if (stock.getMilk() < coffee.getMilk())
            System.out.println("Sorry, not enough milk!");
        else if (stock.getBeans() < coffee.getBeans())
            System.out.println("Sorry, not enough beans!");
        else if (stock.getCups() < 1)
            System.out.println("Sorry, not enough cups!");
        else {
            System.out.println("I have enough resources, making you a coffee!");
            mess++;
            stock.setWater(-1 * coffee.getWater());
            stock.setMilk(-1 * coffee.getMilk());
            stock.setBeans(-1 * coffee.getBeans());
            stock.setCups(-1);
            stock.setMoney(coffee.getMoney());
        }
    }

    private static void buy(Resources stock, Scanner scanner) {
        switch (scanner.nextInt()) {
            case 1:
                makeCoffee(stock, new Coffee.Espresso());
                break;
            case 2:
                makeCoffee(stock, new Coffee.Latte());
                break;
            case 3:
                makeCoffee(stock, new Coffee.Cappuccino());
                break;
            default:
                System.out.println("Wrong input");
        }
    }

    static void fill(Resources stock, Scanner scanner) {

        System.out.println("Write how many ml of water you want to add: ");
        stock.setWater(scanner.nextInt());
        System.out.println("Write how many ml of milk you want to add: ");
        stock.setMilk(scanner.nextInt());
        System.out.println("Write how many grams of coffee beans you want to add: ");
        stock.setBeans(scanner.nextInt());
        System.out.println("Write how many disposable cups you want to add:");
        stock.setCups(scanner.nextInt());
    }
}



class Resources {
    private int water;
    private int milk;
    private int beans;
    private int money;
    private int cups;

    Resources(){
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.money = 550;
        this.cups = 9;
    }

    void setWater(int water) {
        this.water += water;
    }

    void setMilk(int milk) {
        this.milk += milk;
    }

    void setBeans(int beans) {
        this.beans += beans;
    }

    void setMoney(int money) {
        this.money += money;
    }

    void setCups(int cups) {
        this.cups += cups;
    }

    int getWater() {
        return water;
    }

    int getMilk() {
        return milk;
    }

    int getBeans() {
        return beans;
    }
    int getMoney() {
        return money;
    }

    int getCups() {
        return cups;
    }

    void getInfo(){
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                
                """, water, milk, beans, cups, money );
    }
}



class Coffee{
    private final int water;
    private final int milk;
    private final int beans;
    private final int money;

    Coffee(){
        this.water = 0;
        this.milk = 0;
        this.beans = 0;
        this.money = 0;
    }

    public Coffee(int water, int milk, int beans, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.money = money;
    }

    int getWater() {
        return water;
    }

    int getMilk() {
        return milk;
    }

    int getBeans() {
        return beans;
    }

    int getMoney() {
        return money;
    }

    static class Espresso extends Coffee {

        Espresso(){
            super(250, 0, 16, 4);
        }

    }
    static class Latte extends Coffee {

        Latte(){
            super(350, 75, 20, 7);
        }
    }
    static class Cappuccino extends Coffee {

        Cappuccino() {
            super(200,100,12,6);
        }

    }




}
