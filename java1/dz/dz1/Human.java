package java1.dz.dz1;

import java.util.Random;

public class Human implements Runable, Rideable, Swimable{
    private String name;
    private int canSwim = (int) ((Course.SWIM_DISTANCE * 0.5) + (new Random().nextInt(Course.SWIM_DISTANCE)));
    private int canRun = (int) ((Course.RUN_DISTANCE * 0.5) + (new Random().nextInt(Course.RUN_DISTANCE)));
    private int canRide = (int) ((Course.RIDE_DISTANCE * 0.5) + (new Random().nextInt(Course.RIDE_DISTANCE)));

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean ride(int distance) {
        return distance <= canRide;
    }

    @Override
    public boolean run(int distance) {
        return distance <= canRun;
    }

    @Override
    public boolean swim(int distance) {
        return distance <= canSwim;
    }
}
