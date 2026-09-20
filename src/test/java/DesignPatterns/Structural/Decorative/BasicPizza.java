package DesignPatterns.Structural.Decorative;

public class BasicPizza implements Pizza{
    @Override
    public String description() {
        return "Basic Pizza, ";
    }

    @Override
    public int getCost() {
        return 100;
    }
}
