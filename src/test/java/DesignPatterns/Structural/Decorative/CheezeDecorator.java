package DesignPatterns.Structural.Decorative;

public class CheezeDecorator extends PizzaDecorator {


    public CheezeDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String description() {
        return pizza.description()+ "Cheese Added, ";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 20;
    }
}
