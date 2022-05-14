package java1.dz.dz1;

public class App {
    public static void main(String[] args) {
        Course c = new Course(
                Course.CourseType.RIDE,
                Course.CourseType.RUN,
                Course.CourseType.SWIM,
                Course.CourseType.RUN,
                Course.CourseType.SWIM);
        Team team = new Team(
                "We are the champions",
                new Human("Sasha"),
                new Human("Max"),
                new Human("Peter"),
                new Human("Vasia"));
        c.doIt(team);
        team.showResults();
    }
}
