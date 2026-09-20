package DesignPatterns.Structural.Decorative;

public abstract class PizzaDecorator implements Pizza{
    Pizza pizza;  //Important

    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }
}
