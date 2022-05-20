package java1.dz.dz3;

import javax.jws.Oneway;
import java.util.ArrayList;

public class Box <T extends Fruit>{
    private ArrayList<T> fruits = new ArrayList<>();

    public ArrayList<T> getFruits() {
        return fruits;
    }

    public void addFruit(T fruit) {
        this.fruits.add(fruit);
    }

    public void addFruits(ArrayList<T> fruits) {
        if (this.fruits == fruits) return;
        this.fruits.addAll(fruits);
        fruits.clear();
    }

    public float getWeight() {
        float weight = 0.0f;
        if (fruits.size() == 0) return 0.0f;
        for (T fruit : fruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<? extends Fruit> another) {
        return this.getWeight() == another.getWeight();
    }
}
