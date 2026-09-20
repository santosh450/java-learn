package DesignPatterns.Structural.Decorative;

public class MushroomDecorator extends PizzaDecorator {


    public MushroomDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String description() {
        return pizza.description()+ "Mushroom Added, ";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 30;
    }
}
