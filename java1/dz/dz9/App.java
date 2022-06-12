package java1.dz.dz9;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        Course course1 = new Course();
        Course course2 = new Course();
        Course course3 = new Course();
        Course course4 = new Course();
        Course course5 = new Course();
        Course course6 = new Course();
        Course course7 = new Course();

        Student student1 = new Student("Maxim", Arrays.asList(course1, course2, course3));
        Student student2 = new Student("Sasha", Arrays.asList(course1));
        Student student3 = new Student("Ilia", Arrays.asList(course2, course3, course4));
        Student student4 = new Student("Masha", Arrays.asList(course5, course6, course7));
        Student student5 = new Student("Kirill", Arrays.asList(course1, course5, course7));
        Student student6 = new Student("Lena", Arrays.asList(course1, course6, course2, course5));
        Student student7 = new Student("Vasilisa", Arrays.asList(course1, course2, course3, course5, course7, course4));
        Student student8 = new Student("Denis", Arrays.asList(course1, course2, course3, course4, course5, course6, course7));
        Student student9 = new Student("Alexey", Arrays.asList(course7, course6, course5));
        Student student10 = new Student("Dima", Arrays.asList(course5, course4, course3));

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5, student6, student7, student8, student9, student10);

        //1. Написать функцию, принимающую список Student и возвращающую список уникальных курсов,
        // на которые подписаны студенты.

        System.out.println("\n\n#1");
        students.stream()
                .map(s -> s.getAllCourses())
                .flatMap(c -> c.stream())
                .distinct()
                .forEach(System.out::println);

        //2. Написать функцию, принимающую на вход список Student и возвращающую список из трех
        // самых любознательных (любознательность определяется количеством курсов).
        System.out.println("\n\n#2");

        List<Integer> list = students.stream()
                .map(c -> c.getAllCourses().size())
                .sorted((p1, p2) -> p2 - p1)
                .distinct()
                .limit(3)
                .collect(Collectors.toList());
        for (Student student : students) {
            for (Integer i : list) {
                if (i == student.getAllCourses().size()) {
                    System.out.println(student.getName());
                }
            }
        }

        //3. Написать функцию, принимающую на вход список Student и экземпляр Course,
        // возвращающую список студентов, которые посещают этот курс.
        System.out.println("\n\n#3");
        students.stream()
                .forEach(s -> System.out.println(s.getAllCourses().contains(course3) ? s.getName() : "" ));



    }
}
