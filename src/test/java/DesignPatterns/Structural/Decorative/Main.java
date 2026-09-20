package DesignPatterns.Structural.Decorative;

public class Main {

    public static void main(String[] args) {

        Pizza pizza = new BasicPizza();
        System.out.println(pizza.description()+" "+pizza.getCost());

        pizza = new CheezeDecorator(pizza);
        System.out.println(pizza.description()+" "+pizza.getCost());

        pizza = new MushroomDecorator(pizza);
        System.out.println(pizza.description()+" "+pizza.getCost());

    }
}
