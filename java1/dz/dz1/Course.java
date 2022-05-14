package java1.dz.dz1;

public class Course {
    public static int RUN_DISTANCE = 10000;
    public static int RIDE_DISTANCE = 20000;
    public static int SWIM_DISTANCE = 1000;
    CourseType[] courseTypes;

    public Course(CourseType... courseTypes) {
        this.courseTypes = courseTypes;
    }

    public void doIt(Team team) {
        for (int i = 0; i < courseTypes.length; i++) {
            switch (courseTypes[i]) {
                case RUN:
                    team.run(RUN_DISTANCE);
                    break;
                case RIDE:
                    team.ride(RIDE_DISTANCE);
                    break;
                case SWIM:
                    team.swim(SWIM_DISTANCE);
                    break;
            }
        }
    }

    public enum CourseType {
        RUN,RIDE, SWIM
    }
}
