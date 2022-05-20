package java1.dz.dz3;

public class App {
    public static void main(String[] args) {
        Box<Apple> apples1 = new Box<>();
        Box<Apple> apples2 = new Box<>();
        Box<Orange> orange1 = new Box<>();
        Box<Orange> orange2 = new Box<>();
        for (int i = 0; i < 50; i++) {
            apples1.addFruit(new Apple());
            apples2.addFruit(new Apple());
            orange1.addFruit(new Orange());
            orange2.addFruit(new Orange());
        }

        apples1.addFruits(apples1.getFruits());
        System.out.println("Вес первой коробки яблок = " + apples1.getWeight());

        apples1.addFruits(apples2.getFruits());
        System.out.println("Вес первой коробки яблок, после добавления в нее второй = " + apples1.getWeight());
        System.out.println("Вес второй коробки яблок равен " + apples2.getWeight());

        System.out.println();
        orange1.addFruits(orange1.getFruits());
        System.out.println("Вес первой коробки апельсинов = " + orange1.getWeight());

        orange1.addFruits(orange2.getFruits());
        System.out.println("Вес первой коробки апельсинов, после добавления в нее второй = " + orange1.getWeight());
        System.out.println("Вес второй коробки апельсинов равен " + orange2.getWeight());
    }
}
