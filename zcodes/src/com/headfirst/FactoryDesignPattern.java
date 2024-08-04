package com.headfirst;

import java.io.IOException;

public class FactoryDesignPattern {
    public static void main(String[] args) throws IOException {
        PizzaStore pizzaStore = new NyStylePizzaStore();
        pizzaStore.orderPizza("cheese");
    }
}

abstract class PizzaStore { // Pizza Outlet
    SimplePizzaFactory simplePizzaFactory;

    abstract Pizza createPizza(String type);

    Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        if (pizza == null) throw new RuntimeException("Invalid Pizza type");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}

class NyStylePizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String type) {
        if (type.equals("Cheese")) {
            return new NYCheesePizza();
        } else if (type.equals("Butter")) {
            return new NYButterPizza();
        }
        return null;
    }
}

class ChicagoStylePizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("Cheese")) {
            return new ChicagoCheesePizza();
        } else if (type.equals("Butter")) {
            return new ChicagoButterPizza();
        }
        return null;
    }
}

class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("PeporininPizza")) {
            pizza = new PeporiniPizza();
        } else if (type.equals("CheesePizza")) {
            pizza = new CheesePizza();
        } else {
            pizza = new PopCornWithCheese();
        }
        return pizza;
    }

}

abstract class Pizza {


    abstract void prepare();

    void bake() {
        System.out.println("Baking the pizza");
    }

    void cut() {
        System.out.println("Cutting the slices");
    }

    void box() {
        System.out.println("Packing the pizza");
    }


}

class PeporiniPizza extends Pizza {
    @Override
    void prepare() {

    }
}

class CheesePizza extends Pizza {
    @Override
    void prepare() {

    }
}

class PopCornWithCheese extends Pizza {
    @Override
    void prepare() {

    }
}

class NYCheesePizza extends Pizza {
    @Override
    void prepare() {

    }
}

class NYButterPizza extends Pizza {
    @Override
    void prepare() {

    }
}

class ChicagoCheesePizza extends Pizza {
    @Override
    void prepare() {

    }
}

class ChicagoButterPizza extends Pizza {
    @Override
    void prepare() {

    }
}